import tkinter as tk
from tkinter import messagebox, ttk
import numpy as np
import threading
import time
import random
import inference as inf
from Algorithms import backtracking_search, return_moves, is_complete
from inference import backtracking_with_inference, return_moves_inference
from Arc_consistency import Arc_consistency, return_moves_from_arc

moves = list()

class SudokuGUI:
    def __init__(self, root):
        self.root = root
        self.root.title("Sudoku AI Solver")
        self.root.geometry("450x600")

        self.cells = {}
        self.fixed_cells = set()

        self.selected_algo = tk.StringVar(value="Backtracking")
        self.selected_algo.trace_add("write", self.update_button_states)
        
        self.selected_mode = tk.StringVar(value="Mode 1")
        self.empty_places = tk.StringVar(value="50")

        self.grid_frame = None
        self.control_frame = None
        self.btn_arc_tree = None

        self.setup_initial_selection()

    def setup_initial_selection(self):
        self.choice_frame = tk.Frame(self.root)
        self.choice_frame.pack(expand=True)

        tk.Label(self.choice_frame, text="Select Algorithm:", font=("Arial", 12, "bold")).pack(pady=10)

        ttk.Combobox(
            self.choice_frame,
            textvariable=self.selected_algo,
            values=["Backtracking", "Backtracking with Inference"],
            state="readonly"
        ).pack()

        tk.Label(self.choice_frame, text="Select Mode:", font=("Arial", 12, "bold")).pack(pady=10)

        ttk.Combobox(
            self.choice_frame,
            textvariable=self.selected_mode,
            values=["Mode 1 (Demo)", "Mode 2 (User Input)"],
            state="readonly"
        ).pack()

        tk.Label(
            self.choice_frame,
            text="How many empty places:",
            font=("Arial", 12, "bold")
        ).pack(pady=10)

        tk.Entry(
            self.choice_frame,
            textvariable=self.empty_places,
            width=10,
            justify="center"
        ).pack()

        tk.Button(
            self.choice_frame,
            text="Start Game",
            command=self.start_game,
            bg="#4CAF50",
            fg="white",
            font=("Arial", 10, "bold"),
            width=15
        ).pack(pady=20)

    def update_button_states(self, *args):

        try:
            if self.btn_arc_tree and self.btn_arc_tree.winfo_exists():
                if self.selected_algo.get() == "Backtracking":
                    self.btn_arc_tree.config(state="disabled")
                else:
                    self.btn_arc_tree.config(state="normal")
        except (AttributeError, tk.TclError):
            pass

    def start_game(self):
        self.choice_frame.destroy()
        self.create_grid()

        if "Mode 1" in self.selected_mode.get():
            self.generate_fixed_puzzle()

        self.create_controls()
        self.update_button_states()

    def create_grid(self):
        self.grid_frame = tk.Frame(self.root)
        self.grid_frame.pack(pady=10)

        for r in range(9):
            for c in range(9):
                cell = tk.Entry(
                    self.grid_frame,
                    width=3,
                    font=("Arial", 18),
                    justify="center"
                )
                cell.grid(row=r, column=c, padx=1, pady=1)
                self.cells[(r, c)] = cell

    def set_cell_value(self, r, c, val, color="black", fixed=False):
        cell = self.cells[(r, c)]
        cell.config(state="normal")
        cell.delete(0, tk.END)

        if val != 0:
            cell.insert(0, str(val))

        if fixed:
            cell.config(state="disabled", disabledforeground=color)
        else:
            cell.config(fg=color)

    def load_board(self, board):
        self.fixed_cells.clear()
        for r in range(9):
            for c in range(9):
                val = board[r, c]
                if val != 0:
                    self.set_cell_value(r, c, val, "black", True)
                    self.fixed_cells.add((r, c))
                else:
                    self.set_cell_value(r, c, 0, "black", False)

    def create_controls(self):
        self.control_frame = tk.Frame(self.root)
        self.control_frame.pack(pady=10)

        row1 = tk.Frame(self.control_frame)
        row1.pack(pady=3)

        row2 = tk.Frame(self.control_frame)
        row2.pack(pady=3)

        tk.Button(row1, text="Solve", command=self.run_solver, width=10, height=2).pack(side=tk.LEFT, padx=2)
        tk.Button(row1, text="Reset", command=self.reset_board, width=10, height=2).pack(side=tk.LEFT, padx=2)
        tk.Button(row1, text="Check", command=self.check_solvable, width=10, height=2).pack(side=tk.LEFT, padx=2)
        tk.Button(row1, text="Arc", command=self.show_arc_board, width=10, height=2).pack(side=tk.LEFT, padx=2)
        
        self.btn_arc_tree = tk.Button(row1, text="Arc Tree", command=self.show_arc_tree_window, width=10, height=2)
        self.btn_arc_tree.pack(side=tk.LEFT, padx=2)

        buttons2 = [
            ("Random", self.generate_random_puzzle),
            ("Random unsafe", self.generate_random_unsure_board),
            ("Back", self.go_back)
        ]

        for t, c in buttons2:
            tk.Button(row2, text=t, command=c, width=17, height=2).pack(side=tk.LEFT, padx=2)

    def show_arc_tree_window(self):
        tree_window = tk.Toplevel(self.root)
        tree_window.title("Arc Tree - tree_with_levels")
        tree_window.geometry("600x400")

        text_area = tk.Text(tree_window, wrap='none', font=("Courier", 10))
        text_area.pack(expand=True, fill='both', padx=10, pady=10)

        yscroll = tk.Scrollbar(text_area, orient="vertical", command=text_area.yview)
        yscroll.pack(side="right", fill="y")
        text_area.configure(yscrollcommand=yscroll.set)

        if not hasattr(inf, 'tree_with_levels') or not inf.tree_with_levels:
            text_area.insert(tk.END, "No tree data available.\nRun 'Solve' with 'Inference' first.")
        else:
            for level, boards in inf.tree_with_levels.items():
                text_area.insert(tk.END, f"--- LEVEL {level} ---\n")
                for idx, b in enumerate(boards):
                    text_area.insert(tk.END, f"Board {idx}:\n{b}\n")
                text_area.insert(tk.END, "\n" + "=" * 30 + "\n")

        text_area.config(state="disabled")

    def show_arc_board(self):
        threading.Thread(target=self.arc_thread, daemon=True).start()

    def arc_thread(self):
        board = self.get_board().copy()
        result = Arc_consistency(0, 0, 10, board)
        arc_moves = return_moves_from_arc()

        if result is None:
            self.root.after(0, lambda: messagebox.showerror("Arc", "Not solvable"))
            return

        self.root.after(0, lambda: self.animate_arc(arc_moves))

    def animate_arc(self, arc_moves, i=0):
        if i >= len(arc_moves):
            return
        self.update_arc_gui(arc_moves[i])
        self.root.after(80, lambda: self.animate_arc(arc_moves, i + 1))

    def update_arc_gui(self, board):
        for r in range(9):
            for c in range(9):
                if (r, c) in self.fixed_cells:
                    continue
                val = board[r, c]
                if val != 0:
                    self.set_cell_value(r, c, val, "red", False)
                else:
                    self.set_cell_value(r, c, 0, "black", False)

    def get_board(self):
        board = np.zeros((9, 9), dtype=int)
        for r in range(9):
            for c in range(9):
                v = self.cells[(r, c)].get().strip()
                if v.isdigit():
                    board[r, c] = int(v)
        return board

    def run_solver(self):
        threading.Thread(target=self.solve_thread, daemon=True).start()

    def solve_thread(self):
        global moves
        moves.clear()
        board = self.get_board().copy()
        algo = self.selected_algo.get()
        start = time.time()

        if algo == "Backtracking":
            result = backtracking_search(board)
            moves = return_moves()
        else:
            inf.moves.clear()
            if hasattr(inf, 'tree_with_levels'):
                inf.tree_with_levels.clear()
            result = backtracking_with_inference(board, 0)
            moves = return_moves_inference()

        end = time.time()

        if result is None:
            self.root.after(0, lambda: messagebox.showerror("Error", "No solution"))
            return

        self.root.after(0, self.animate_moves)
        self.root.after(0, lambda: messagebox.showinfo("Solved", f"Time: {end - start:.4f}"))

    def animate_moves(self, i=0):
        if i >= len(moves):
            return
        self.update_gui(moves[i])
        self.root.after(30, lambda: self.animate_moves(i + 1))

    def update_gui(self, board):
        for r in range(9):
            for c in range(9):
                if (r, c) in self.fixed_cells:
                    continue
                val = board[r, c]
                if val != 0:
                    self.set_cell_value(r, c, val, "blue", False)
                else:
                    self.set_cell_value(r, c, 0, "black", False)

    def check_solvable(self):
        board = self.get_board().copy()
        result = Arc_consistency(0, 0, 10, board)
        if result is None:
            messagebox.showerror("Check", "NOT solvable")
        elif is_complete(result):
            messagebox.showinfo("Check", "Solved")
        else:
            messagebox.showinfo("Check", "Maybe solvable")

    def generate_fixed_puzzle(self):
        try:
            empty_count = int(self.empty_places.get())
        except ValueError:
            empty_count = 50

        solved_board = np.array([
            [5, 3, 4, 6, 7, 8, 9, 1, 2],
            [6, 7, 2, 1, 9, 5, 3, 4, 8],
            [1, 9, 8, 3, 4, 2, 5, 6, 7],
            [8, 5, 9, 7, 6, 1, 4, 2, 3],
            [4, 2, 6, 8, 5, 3, 7, 9, 1],
            [7, 1, 3, 9, 2, 4, 8, 5, 6],
            [9, 6, 1, 5, 3, 7, 2, 8, 4],
            [2, 8, 7, 4, 1, 9, 6, 3, 5],
            [3, 4, 5, 2, 8, 6, 1, 7, 9]
        ])

        puzzle = solved_board.copy()
        pos = [(r, c) for r in range(9) for c in range(9)]
        random.shuffle(pos)

        for i in range(min(empty_count, 81)):
            r, c = pos[i]
            puzzle[r, c] = 0
        self.load_board(puzzle)

    def generate_random_puzzle(self):
        try:
            empty_count = int(self.empty_places.get())
        except ValueError:
            empty_count = 50

        board = np.zeros((9, 9), dtype=int)
        solved = backtracking_search(board.copy())

        if solved is None:
            messagebox.showerror("Error", "Could not generate")
            return

        puzzle = solved.copy()
        pos = [(r, c) for r in range(9) for c in range(9)]
        random.shuffle(pos)

        for i in range(min(empty_count, 81)):
            r, c = pos[i]
            puzzle[r, c] = 0
        self.load_board(puzzle)

    def generate_random_unsure_board(self):
        try:
            empty_count = int(self.empty_places.get())
        except ValueError:
            empty_count = 50

        board = np.random.randint(1, 10, (9, 9))
        pos = [(r, c) for r in range(9) for c in range(9)]
        random.shuffle(pos)

        for i in range(min(empty_count, 81)):
            r, c = pos[i]
            board[r, c] = 0
        self.load_board(board)

    def reset_board(self):
        for c in self.cells.values():
            c.config(state="normal", fg="black")
            c.delete(0, tk.END)
        self.fixed_cells.clear()

    def go_back(self):
        if self.grid_frame: self.grid_frame.destroy()
        if self.control_frame: self.control_frame.destroy()
        self.cells.clear()
        self.fixed_cells.clear()
        self.setup_initial_selection()

if __name__ == "__main__":
    root = tk.Tk()
    app = SudokuGUI(root)
    root.mainloop()
import tkinter as tk
import numpy as np
from Algorithms import *
from node import node
from tkinter import messagebox
from try1 import *
import time
import heurestic_function as asd
from tree import *

ROWS = 6
COLS = 7
EMPTY = 0
HUMAN = -1
AI = 1

CELL = 80
PAD = 10

BG = "#f5f5f5"
BOARD_BG = "#2c2c2c"
EMPTY_COLOR = "#444444"
RED = "#ff4d4d"
YELLOW = "#ffd633"
TEXT = "#111111"

settings = {
    "K": 5,
    "color": "Yellow",
    "algorithm": "minimax",
    "show_tree": False
}


class Window1(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Connect 4")
        self.geometry("340x460")
        self.configure(bg=BG)

        title_font = ("Arial", 26, "bold")
        label_font = ("Arial", 14)
        option_font = ("Arial", 13)

        tk.Label(self, text="Connect 4",
                 font=title_font,
                 bg=BG, fg=TEXT).pack(pady=20)

        tk.Label(self, text="Depth", bg=BG, fg=TEXT,
                 font=label_font).pack()

        self.k = tk.IntVar(value=3)
        tk.Spinbox(self, from_=1, to=10,
                   textvariable=self.k,
                   font=option_font,
                   width=10).pack(pady=10)

        tk.Label(self, text="Color", bg=BG, fg=TEXT,
                 font=label_font).pack()

        self.color = tk.StringVar(value="Yellow")

        tk.Radiobutton(self, text="Red", variable=self.color,
                       value="Red", bg=BG,
                       font=option_font).pack()

        tk.Radiobutton(self, text="Yellow", variable=self.color,
                       value="Yellow", bg=BG,
                       font=option_font).pack()

        tk.Label(self, text="Show Search Tree",
                 bg=BG, fg=TEXT,
                 font=label_font).pack(pady=10)

        self.show_tree = tk.StringVar(value="No")

        tk.Radiobutton(self, text="Yes", variable=self.show_tree,
                       value="Yes", bg=BG,
                       font=option_font).pack()

        tk.Radiobutton(self, text="No", variable=self.show_tree,
                       value="No", bg=BG,
                       font=option_font).pack()

        tk.Button(self, text="Start",
                  command=self.next_window,
                  font=("Arial", 14),
                  width=15).pack(pady=20)

    def next_window(self):
        settings["K"] = self.k.get()
        settings["color"] = self.color.get()
        settings["show_tree"] = self.show_tree.get() == "Yes"
        self.destroy()
        Window2()


class Window2(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("AI Mode")
        self.geometry("340x360")
        self.configure(bg=BG)

        title_font = ("Arial", 20, "bold")
        option_font = ("Arial", 14)

        tk.Label(self, text="AI Mode",
                 bg=BG, fg=TEXT,
                 font=title_font).pack(pady=20)

        self.algo = tk.StringVar(value="minimax")

        options = [
            ("Minimax", "minimax"),
            ("Alpha Beta", "alpha_beta"),
            ("Expected", "expected")
        ]

        for t, v in options:
            tk.Radiobutton(self, text=t,
                           variable=self.algo,
                           value=v,
                           bg=BG,
                           font=option_font).pack(anchor="w", padx=50, pady=5)

        tk.Button(self, text="Play",
                  command=self.start,
                  font=("Arial", 14),
                  width=15).pack(pady=25)

    def start(self):
        settings["algorithm"] = self.algo.get()
        self.destroy()
        Window3()


class Window3(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Game")
        self.configure(bg=BG)

        self.tree_window = None
        self.board = np.zeros((ROWS, COLS), dtype=int)
        self.game_over = False

        self.human_color = RED if settings["color"] == "Red" else YELLOW
        self.ai_color = YELLOW if settings["color"] == "Red" else RED

        self.status = tk.StringVar(value="Your Turn")

        tk.Label(self, textvariable=self.status,
                 bg=BG, fg=TEXT,
                 font=("Arial", 14)).pack(pady=8)

        self.canvas = tk.Canvas(self,
                                width=COLS * CELL,
                                height=ROWS * CELL,
                                bg=BOARD_BG,
                                highlightthickness=0)
        self.canvas.pack()

        self.canvas.bind("<Button-1>", self.handle_click)

        self.draw_board()

        if self.ai_color == RED:
            self.status.set("AI Thinking...")
            self.after(250, self.trigger_ai)

    def draw_board(self):
        self.canvas.delete("all")

        for r in range(ROWS):
            for c in range(COLS):
                x0 = c * CELL + PAD
                y0 = r * CELL + PAD
                x1 = (c + 1) * CELL - PAD
                y1 = (r + 1) * CELL - PAD

                val = self.board[r][c]

                color = EMPTY_COLOR
                if val == HUMAN:
                    color = self.human_color
                elif val == AI:
                    color = self.ai_color

                self.canvas.create_oval(x0, y0, x1, y1,
                                        fill=color, outline="")

    def handle_click(self, event):
        if self.game_over:
            return

        col = event.x // CELL

        if not self.is_valid_move(col):
            return

        self.make_move(col, HUMAN)
        self.draw_board()

        if self.check_completed_board(self.board):
            human_score, computer_score = self.score_for_players()
            self.end_game(self.get_winner(), computer_score, human_score)
            return

        self.status.set("AI Thinking...")
        self.after(250, self.trigger_ai)

    def trigger_ai(self):
        if self.game_over:
            return

        col = self.ai_move()
        self.make_move(col, AI)
        self.draw_board()

        if self.check_completed_board(self.board):
            human_score, computer_score = self.score_for_players()
            self.end_game(self.get_winner(), computer_score, human_score)
            return

        self.status.set("Your Turn")

    def is_valid_move(self, col):
        return self.board[0][col] == EMPTY

    def make_move(self, col, player):
        for r in range(ROWS - 1, -1, -1):
            if self.board[r][col] == EMPTY:
                self.board[r][col] = player
                break

    def ai_move(self):
        current_node = node(self.board.copy())
        depth = settings["K"]
        algo = settings["algorithm"]
        best_node = None
        tree = None

        try:
            if algo == "minimax":
                start=time.time()
                result,tree, num_of_node_expanded = minmax_algorithm(current_node, depth)
                end=time.time()
                print(f'time = {(end-start)*1000} ms')
                print(f'Number of nodes expanded: {num_of_node_expanded}')
                
                best_node = result if isinstance(result, node) else result

            elif algo == "alpha_beta":
                start=time.time()
                best_node,tree, num_of_node_expanded = decision(current_node, depth, 0)
                end=time.time()
                print(f'time = {(end-start)*1000} ms')
                print(f'Number of nodes expanded: {num_of_node_expanded}')
                

            else:
                start=time.time()
                best_node, tree, num_of_node_expanded = expectiminimax_decision(current_node, depth, current_node.get_col(1))
                end=time.time()
                print(f'time = {(end-start)*1000} ms')
                print(f'Number of nodes expanded: {num_of_node_expanded}')

        except Exception as e:
            print("AI error:", e)

        if settings["show_tree"] and tree:
            if self.tree_window is None or not tk.Toplevel.winfo_exists(self.tree_window):
                self.tree_window = TreeVisualizer(self.human_color, self.ai_color)
            self.tree_window.update_tree(tree)

        if best_node:
            col = self.find_diff(best_node.board)
            if self.is_valid_move(col):
                return col

    def score_for_players(self):
        return (number_of_connected_4_human(self.board.copy()),
                number_of_connected_4_computer(self.board.copy()))

    def find_diff(self, new_board):
        for c in range(COLS):
            if not np.array_equal(self.board[:, c], new_board[:, c]):
                return c
        return 0

    def check_completed_board(self, board):
        return not np.any(board == 0)

    def get_winner(self):
        h, a = self.score_for_players()
        if a > h:
            return "AI Wins"
        elif h > a:
            return "You Win"
        return "Draw"

    def end_game(self, msg, computer_score, human_score):
        self.game_over = True
        self.status.set(msg)
        messagebox.showinfo("GAME ENDED",
                            f"{msg}\nAI Score: {computer_score}\nYour Score: {human_score}")


if __name__ == "__main__":
    Window1().mainloop()
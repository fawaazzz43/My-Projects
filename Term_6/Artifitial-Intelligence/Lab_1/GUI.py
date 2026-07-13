import tkinter as tk
from tkinter import messagebox
import DFS
import BFS
import IDS
import A_star
import Path_to_goal
from try2 import node

board = "120354678" 


def run_algorithm(selected_algorithm, entry, grid_labels):
    board_str = entry.get().strip()
    if len(board_str) != 9 or not board_str.isdigit():
        messagebox.showerror("Error", "Enter a 9-digit integer (example: 120354678)")
        return
    board_int = int(board_str)

    if selected_algorithm.startswith("DFS"):
        found, goal, visited, level, run_time = DFS.DFS_algorithm(node(board_int, None))
        if found:
            goal_path, path_cost = Path_to_goal.path_to_goal(goal)
            print(goal_path)
            entry.delete(0, tk.END)
            entry.insert(0, "012345678")
            show_array(entry, grid_labels)
            messagebox.showinfo("Result",
                f"DFS: Goal Found!\nNodes expanded: {len(visited)}\nSearch depth: {level}\nCost of the path: {path_cost}\nTime: {run_time} s")
        else:
            messagebox.showinfo("Result", "DFS: Goal Not Found!")

    elif selected_algorithm.startswith("BFS"):
        found, nodes_expanded, depth, time_taken, goal = BFS.BFS(node(board_int, None))
        if found:
            goal_path, path_cost = Path_to_goal.path_to_goal(goal)
            print(goal_path)
            entry.delete(0, tk.END)
            entry.insert(0, "012345678")
            show_array(entry, grid_labels)
            messagebox.showinfo("Result",
                f"BFS: Goal Found!\nNodes expanded: {nodes_expanded}\nSearch depth: {depth}\nCost of the path: {path_cost}\nTime: {time_taken} s")
        else:
            messagebox.showinfo("Result", "BFS: Goal Not Found!")

    elif selected_algorithm.startswith("IDS"):
        found, nodes_expanded, depth, time_taken, path_cost, path = IDS.IDS(board_int)
        if found:
            print(path)
            entry.delete(0, tk.END)
            entry.insert(0, "012345678")
            show_array(entry, grid_labels)
            messagebox.showinfo("Result",
                f"IDS: Goal Found!\nNodes expanded: {nodes_expanded}\nSearch depth: {depth}\nCost of the path: {path_cost}\nTime: {time_taken} s")
        else:
            messagebox.showinfo("Result", "IDS: Goal Not Found!")

    elif selected_algorithm.startswith("A*"):
        if "Manhattan" in selected_algorithm:
            found, nodes_expanded, depth, time_taken, path, path_cost = A_star.A_star_manhattan(node(board_int, None))
            heuristic = "Manhattan"
        elif "Euclidean" in selected_algorithm:
            found, nodes_expanded, depth, time_taken, path, path_cost = A_star.A_star_euclidean(node(board_int, None))
            heuristic = "Euclidean"
        if found:
            print(path)
            entry.delete(0, tk.END)
            entry.insert(0, "012345678")
            show_array(entry, grid_labels)
            messagebox.showinfo("Result",
                f"A* ({heuristic}): Goal Found!\nNodes expanded: {nodes_expanded}\nSearch depth: {depth}\nCost of the path: {path_cost}\nTime: {time_taken} s")
        else:
            messagebox.showinfo("Result", f"A* ({heuristic}): Goal Not Found!")

def show_array(entry, grid_labels):
    board_str = entry.get().strip()
    if len(board_str) != 9 or not board_str.isdigit():
        messagebox.showerror("Error", "Enter a 9-digit integer")
        return
    digits = list(board_str)
    for i in range(3):
        for j in range(3):
            val = digits[i*3 + j]
            if val == "0":
                grid_labels[i][j].config(text="", bg="lightgray")
            else:
                grid_labels[i][j].config(text=val, bg="white")

# --- صفحة الشبكة ---
def array_page(selected_algorithm):
    array_win = tk.Toplevel(root)
    array_win.title(f"3x3 Array Grid ({selected_algorithm})")
    array_win.geometry("300x460")

    tk.Label(array_win, text=f"Algorithm: {selected_algorithm}", font=("Arial", 12)).pack(pady=5)
    tk.Label(array_win, text="Enter 9-digit board:").pack()

    entry = tk.Entry(array_win, font=("Arial", 14))
    entry.pack(pady=5)
    entry.insert(0, board)

    frame = tk.Frame(array_win)
    frame.pack(pady=10)

    grid_labels = []
    for i in range(3):
        row = []
        for j in range(3):
            lbl = tk.Label(frame, text="", width=4, height=2, font=("Arial", 18),
                           relief="ridge", borderwidth=2)
            lbl.grid(row=i, column=j, padx=2, pady=2)
            row.append(lbl)
        grid_labels.append(row)

    tk.Button(array_win, text="Show Array", command=lambda: show_array(entry, grid_labels)).pack(pady=5)
    tk.Button(array_win, text="Run Algorithm", command=lambda: run_algorithm(selected_algorithm, entry, grid_labels)).pack(pady=5)
    tk.Button(array_win, text="Back", command=lambda: back_to_algo(array_win)).pack(pady=10)


def back_to_algo(array_win):
    array_win.destroy()
    root.deiconify()


def open_algorithm(algo):
    root.withdraw()
    if algo == "A*":
        choice_win = tk.Toplevel(root)
        choice_win.title("Choose Heuristic")
        choice_win.geometry("250x150")
        tk.Label(choice_win, text="Select Heuristic:", font=("Arial", 12)).pack(pady=10)
        def select_heuristic(heuristic):
            choice_win.destroy()
            array_page(f"A* ({heuristic})")
        tk.Button(choice_win, text="Manhattan", width=12, command=lambda: select_heuristic("Manhattan")).pack(pady=5)
        tk.Button(choice_win, text="Euclidean", width=12, command=lambda: select_heuristic("Euclidean")).pack(pady=5)
    else:
        array_page(algo)

def algorithm_page():
    tk.Label(root, text="Select Algorithm for 8-Puzzle:", font=("Arial", 12)).pack(pady=20)
    algorithms = ["DFS", "BFS", "IDS", "A*"]
    for algo in algorithms:
        tk.Button(root, text=algo, width=10, height=2, command=lambda a=algo: open_algorithm(a)).pack(pady=5)


root = tk.Tk()
root.title("8-Puzzle Algorithm Choice")
root.geometry("400x350")
algorithm_page()
root.mainloop()
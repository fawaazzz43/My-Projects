import tkinter as tk

class TreeVisualizer(tk.Toplevel):
    def __init__(self, human_color, ai_color):
        super().__init__()
        self.title("AI Search Tree Explorer")
        self.geometry("1000x700")
        self.configure(bg="#f0f0f0")

        self.ai_color = ai_color
        self.human_color = human_color

        self.main_canvas = tk.Canvas(self, bg="#f0f0f0", highlightthickness=0)

        self.v_scrollbar = tk.Scrollbar(self, orient="vertical", command=self.main_canvas.yview)
        self.h_scrollbar = tk.Scrollbar(self, orient="horizontal", command=self.main_canvas.xview)

        self.main_canvas.configure(
            yscrollcommand=self.v_scrollbar.set,
            xscrollcommand=self.h_scrollbar.set
        )

        self.scroll_frame = tk.Frame(self.main_canvas, bg="#f0f0f0")

        self.canvas_window = self.main_canvas.create_window(
            (0, 0),
            window=self.scroll_frame,
            anchor="nw"
        )

        self.scroll_frame.bind(
            "<Configure>",
            lambda e: self.main_canvas.configure(scrollregion=self.main_canvas.bbox("all"))
        )

        self.main_canvas.bind("<Configure>", self.on_canvas_configure)

        self.main_canvas.pack(side="left", fill="both", expand=True)
        self.v_scrollbar.pack(side="right", fill="y")
        self.h_scrollbar.pack(side="bottom", fill="x")

        self.main_canvas.bind_all("<MouseWheel>", self.on_vertical_scroll)
        self.main_canvas.bind_all("<Shift-MouseWheel>", self.on_horizontal_scroll)

    def on_canvas_configure(self, event):
        self.main_canvas.configure(scrollregion=self.main_canvas.bbox("all"))

    def on_vertical_scroll(self, event):
        self.main_canvas.yview_scroll(int(-1 * (event.delta / 120)), "units")

    def on_horizontal_scroll(self, event):
        self.main_canvas.xview_scroll(int(-1 * (event.delta / 120)), "units")

    def update_tree(self, tree_dict):
        self.tree_data = tree_dict

        header_frame = tk.Frame(self.scroll_frame, bg="#2c2c2c", pady=10)
        header_frame.pack(fill="x", pady=(20, 0))

        tk.Label(
            header_frame,
            text="AI TURN SUMMARY",
            font=("Arial", 12, "bold"),
            bg="#2c2c2c",
            fg="white"
        ).pack()

        for height in sorted(self.tree_data.keys()):
            level_label = tk.Label(
                self.scroll_frame,
                text=f"Level {height}",
                font=("Arial", 10, "bold"),
                bg="#ddd",
                pady=3
            )
            level_label.pack(fill="x", pady=(10, 0))

            h_frame = tk.Frame(self.scroll_frame, bg="#f0f0f0")
            h_frame.pack(padx=10, pady=5)

            nodes = self.tree_data[height]

            for i, board in enumerate(nodes):
                board = nodes[i].board
                self.draw_mini_board(h_frame, board, i,nodes[i].value)

        self.update_idletasks()
        self.main_canvas.yview_moveto(1.0)
        self.lift()

    def draw_mini_board(self, parent, board, index, value):
        container = tk.Frame(parent, bd=1, relief="ridge", bg="white", padx=4, pady=4)
        container.pack(side="left", padx=3)

        cell = 7
        canvas = tk.Canvas(
            container,
            width=7 * cell,
            height=6 * cell,
            bg="#2c2c2c",
            highlightthickness=0
        )
        canvas.pack()

        for r in range(6):
            for c in range(7):
                val = board[r][c]

                color = "#444444"
                if val == -1:
                    color = self.human_color
                elif val == 1:
                    color = self.ai_color

                x0, y0 = c * cell, r * cell
                canvas.create_oval(
                    x0 + 1,
                    y0 + 1,
                    x0 + cell - 1,
                    y0 + cell - 1,
                    fill=color,
                    outline=""
                )

        tk.Label(container, text=f"n{index}\n{value:.2f}", font=("Arial", 5), bg="white").pack()
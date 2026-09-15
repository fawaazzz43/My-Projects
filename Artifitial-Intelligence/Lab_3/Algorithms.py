import numpy as np
import time

bit_row = [0] * 9
bit_col = [0] * 9
bit_box = [0] * 9


def backtracking_search(assignment: np.ndarray):
    global moves
    moves.clear()
    global bit_row, bit_col, bit_box

    bit_row = [0] * 9
    bit_col = [0] * 9
    bit_box = [0] * 9
    if board_to_bits(assignment) == False:
        return None

    return backtrack(assignment)


moves = list()


def backtrack(assignment: np.ndarray):
    if is_complete(assignment):
        return assignment
    var, domain = select_unassigned_variable(assignment)
    for value in bitwise_to_values(domain):
        if is_consistent(var, value):
            add_to_assignment(var, value, assignment)
            result = backtrack(assignment)
            if result is not None:
                return result
            remove_from_assignment(var, assignment)

    return None


def is_complete(assignment: np.ndarray):
    return np.all(assignment != 0)


def select_unassigned_variable(assignment: np.ndarray):
    best_var = None
    best_domain = None
    best_size = 10

    for i in range(9):
        for j in range(9):
            if assignment[i, j] == 0:
                index = i * 9 + j
                domain = get_domain(index)
                size = domain.bit_count()
                if size < best_size:
                    best_size = size
                    best_domain = domain
                    best_var = index

    return best_var, best_domain


def get_domain(index):
    row = index // 9
    col = index % 9
    box = (row // 3) * 3 + (col // 3)

    used = bit_row[row] | bit_col[col] | bit_box[box]
    domain = (~used) & 0x1FF

    return domain


def bitwise_to_values(bitwise):
    values = []
    for i in range(9):
        if bitwise & (1 << i):
            values.append(i + 1)
    return values


def add_to_assignment(var, value, assignment):
    row = var // 9
    col = var % 9
    box = (row // 3) * 3 + (col // 3)

    bit = 1 << (value - 1)

    assignment[row, col] = value
    moves.append(assignment.copy())
    bit_row[row] |= bit
    bit_col[col] |= bit
    bit_box[box] |= bit


def is_consistent(var, value):
    row = var // 9
    col = var % 9
    box = (row // 3) * 3 + (col // 3)

    bit = 1 << (value - 1)

    return not (bit_row[row] & bit or bit_col[col] & bit or bit_box[box] & bit)


def remove_from_assignment(var, assignment):
    row = var // 9
    col = var % 9
    box = (row // 3) * 3 + (col // 3)

    value = assignment[row, col]
    bit = 1 << (value - 1)

    assignment[row, col] = 0
    if moves:
        moves.pop()
    bit_row[row] &= ~bit
    bit_col[col] &= ~bit
    bit_box[box] &= ~bit


def board_to_bits(board):
    for i in range(9):
        for j in range(9):
            if board[i, j] != 0:
                val = board[i, j]
                bit = 1 << (val - 1)
                box = (i // 3) * 3 + (j // 3)
                if (bit_row[i] & bit) or (bit_col[j] & bit) or (bit_box[box] & bit):
                    return False
                bit_row[i] |= bit
                bit_col[j] |= bit
                bit_box[box] |= bit
    return True

def return_moves ():
    global moves
    return moves
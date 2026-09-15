import time
import numpy as np
from Arc_consistency import *

moves = list()
tree_with_levels : dict[int, list] = {}
def backtracking_with_inference (assignment : np.ndarray,level) :
    global moves
    global tree_with_levels
    if is_complete(assignment):
        return assignment
    i , j , domain = select_unassigned_variable(assignment)
    for value in domain :
        if not is_consistent_value(assignment, i, j, value):
            continue
        copy = assignment.copy()
        copy[i,j] = value
        inferences = Arc_consistency(i,j,value,copy)
        if inferences is not None :
            board=tree_arcs(inferences)
            if level not in tree_with_levels :
                tree_with_levels[level]=[board]
            else:
                tree_with_levels[level].append(board)
            moves.append(copy)
            result = backtracking_with_inference(inferences,level+1)
            if result is not None :
                return result
            moves.pop()
    return None

def is_complete (assignment : np.ndarray) :
    return np.all(assignment != 0)

def select_unassigned_variable ( assignment : np.ndarray) :
    i , j = 0 , 0
    min_size = 10
    best_domain = []
    for index1 in range (9) :
        for index2 in range(9) :
            if assignment[index1,index2] == 0 :
                domain = get_domain_for_cell(assignment,index1,index2)
                size = domain.bit_count()
                if size < min_size:
                    min_size = size
                    best_domain = domain
                    i, j = index1, index2
    return i , j , convert_from_binary_to_arr_in_domain(best_domain)


def get_domain_for_cell ( assignment : np.ndarray , i , j ) :
    row = get_row(assignment,i)
    col = get_col(assignment,j)
    box = get_box(assignment,i,j)

    return get_domain_for_line(row) & get_domain_for_line(col) & get_domain_for_line(box.flatten())

def is_consistent_value(assignment: np.ndarray, i: int, j: int, value: int) -> bool:
    if value in assignment[i, :]:
        return False
    if value in assignment[:, j]:
        return False

    start_row = (i // 3) * 3
    start_col = (j // 3) * 3
    if value in assignment[start_row:start_row + 3, start_col:start_col + 3]:
        return False

    return True

def convert_from_binary_to_arr_in_domain ( num ) :
    domain = []
    for i in range(9):
        if num & (1 << i):
            domain.append(i + 1)
    return domain

def get_row (assignment : np.ndarray , index) :
    return assignment[index , :]

def get_col (assignment : np.ndarray , index) :
    return assignment[:, index]

def get_box (assignment : np.ndarray , index1 , index2) :
    start_row = ( index1 // 3 ) * 3
    start_col = ( index2 // 3 ) * 3

    return assignment[start_row : start_row + 3, start_col : start_col + 3]

def get_domain_for_line(arr):
    not_available = 0
    for i in range(9):
        if arr[i] != 0:
            bit = 1 << (arr[i] - 1)
            not_available |= bit
    return (~not_available) & 0x1FF

# Example where Inference is fast (many empty cells)

# board = np.array([
#     [5, 3, 4, 6, 7, 8, 9, 1, 0],
#     [6, 7, 2, 1, 9, 5, 3, 4, 8],
#     [1, 9, 8, 3, 4, 2, 5, 6, 7],
#     [8, 5, 9, 7, 6, 1, 4, 2, 3],
#     [4, 2, 6, 8, 5, 3, 7, 9, 1],
#     [7, 1, 3, 9, 2, 4, 8, 5, 6],
#     [9, 6, 1, 5, 3, 7, 2, 8, 4],
#     [2, 8, 7, 4, 1, 9, 6, 3, 5],
#     [3, 4, 5, 2, 8, 6, 1, 7, 9]
# ])
#
# start = time.time()
# result = backtracking_with_inference(board)
# if result is not None:
#     print("Solution found:")
#     print(result)
#
# else :
#     print("No solution exists.")
# end = time.time()
# print(f"Time taken: {end - start} seconds")

def return_moves_inference ():
    global moves
    return moves
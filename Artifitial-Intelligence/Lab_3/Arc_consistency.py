from Variable import Variable
from collections import deque
import numpy as np
import math as ma
import time


def fill_queue(array: np.ndarray) -> deque:
    global queue
    global moves
    moves =list()
    queue = deque()


    for i in range(9):
        for j in range(0, 8):
            if array[i, j] == 0:
                for k in range(j + 1, 9):
                    if array[i, k] != 0:
                        queue.append(Variable.unique_variable(i * 9 + j, 0x1FF))
                        queue.append(Variable.unique_variable(i * 9 + k, 1 << (int(array[i, k]) - 1)))
            else:
                for k in range(j + 1, 9):
                    queue.append(Variable.unique_variable(i * 9 + j, 1 << (int(array[i, j]) - 1)))
                    if array[i, k] == 0:
                        queue.append(Variable.unique_variable(i * 9 + k, 0x1FF))
                    else:
                        queue.append(Variable.unique_variable(i * 9 + k, 1 << (int(array[i, k]) - 1)))

  
    for i in range(9):
        for j in range(0, 8):
            if array[j, i] == 0:
                for k in range(j + 1, 9):
                   
                    if array[k, i] != 0:
                        queue.append(Variable.unique_variable(j * 9 + i, 0x1FF))
                        queue.append(Variable.unique_variable(k * 9 + i, 1 << (int(array[k, i]) - 1)))
            else:
                for k in range(j + 1, 9):
                    queue.append(Variable.unique_variable(j * 9 + i, 1 << (int(array[j, i]) - 1)))
                    if array[k, i] == 0:
                        queue.append(Variable.unique_variable(k * 9 + i, 0x1FF))
                    else:
                        queue.append(Variable.unique_variable(k * 9 + i, 1 << (int(array[k, i]) - 1)))

    for i in range(9):
        for j in range(8):

            r1, c1 = j // 3 + (i // 3) * 3, (i % 3) * 3 + j % 3
            if array[r1, c1] == 0:
                for k in range(j + 1, 9):
                    r2, c2 = k // 3 + (i // 3) * 3, (i % 3) * 3 + k % 3
                    if array[r2, c2] != 0:
                        queue.append(Variable.unique_variable(r1 * 9 + c1, 0x1FF))
                        queue.append(Variable.unique_variable(r2 * 9 + c2, 1 << (int(array[r2, c2]) - 1)))
            else:
                for k in range(j + 1, 9):
                    r2, c2 = k // 3 + (i // 3) * 3, (i % 3) * 3 + k % 3
                    queue.append(Variable.unique_variable(r1 * 9 + c1, 1 << (int(array[r1, c1]) - 1)))
                    if array[r2, c2] == 0:
                        queue.append(Variable.unique_variable(r2 * 9 + c2, 0x1FF))
                    else:
                        queue.append(Variable.unique_variable(r2 * 9 + c2, 1 << (int(array[r2, c2]) - 1)))

    return queue


def Arc_consistency(i, j, value, array: np.ndarray):
    if value != 10:
       array[i, j] = value
    Variable.objects = {}
    global queue
    fill_queue(array)
    i = 0
    while queue:
        i += 1
        if Revise(queue.popleft(), queue.popleft(), array) == False:
            return None
    return array


def Revise(x: Variable, y: Variable, array):
    global moves
    if x.domain.bit_count() == 1 and y.domain.bit_count() == 1:
        if (x.domain & y.domain) == 0:
            return True
        else:
            x.domain = 0
            y.domain = 0
            return False
    if x.domain.bit_count() == 1:
        y.domain = y.domain & (~x.domain)
        if y.domain.bit_count() == 1:
            array[y.index // 9, y.index % 9] = y.domain.bit_length()
            moves.append(array.copy())
            update_queue(y, array)
    elif y.domain.bit_count() == 1:
        x.domain = x.domain & (~y.domain)
        if x.domain.bit_count() == 1:
            array[x.index // 9, x.index % 9] = x.domain.bit_length()
            moves.append(array.copy())
            update_queue(x, array)

    if y.domain.bit_count() == 0 or x.domain.bit_count() == 0:
        return False
    return True


def update_queue(var: Variable, array):
    global queue
    row = var.index // 9
    col = var.index % 9
    box = (row // 3) * 3 + col // 3
    for j in range(9):
        if j == col:
            continue
        queue.append(var)
        if array[row, j] == 0:
            queue.append(Variable.unique_variable(row * 9 + j, 0x1FF))
        else:
            queue.append(Variable.objects[row * 9 + j])

    j = 0
    for j in range(9):
        if row == j:
            continue
        queue.append(var)
        if array[j, col] == 0:
            queue.append(Variable.unique_variable(j * 9 + col, 0x1FF))
        else:
            queue.append(Variable.objects[j * 9 + col])

    j = 0
    for j in range(9):
        if (row == j // 3 + (box // 3) * 3 and col == (box % 3) * 3 + j % 3):
            continue
        queue.append(var)
        if array[j // 3 + (box // 3) * 3, (box % 3) * 3 + j % 3] == 0:
            queue.append(Variable.unique_variable((j // 3 + (box // 3) * 3) * 9 + (box % 3) * 3 + j % 3, 0x1FF))
        else:
            queue.append(Variable.objects[(j // 3 + (box // 3) * 3) * 9 + (box % 3) * 3 + j % 3])


def return_moves_from_arc():
    global moves
    return moves
'''
board = np.array([
    [8, 0, 9, 5, 0, 1, 7, 3, 6],
    [2, 0, 7, 0, 6, 3, 0, 0, 0],
    [1, 6, 0, 0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 9, 0, 4, 0, 7],
    [0, 9, 0, 3, 0, 7, 0, 2, 0],
    [7, 0, 6, 0, 8, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 6, 3],
    [0, 0, 0, 9, 3, 0, 5, 0, 2],
    [5, 3, 2, 6, 0, 4, 8, 0, 9]
])

start = time.time()
print(Arc_consistency(board))
end = time.time()
print((end-start)*1000)

print(board)
for i in range (9) :
    for j in range (9):
        if board[i,j] == 0:
            if Variable.objects[i*9+j].domain.bit_count() > 1 :
                board[i,j]=10
            elif Variable.objects[i*9+j].domain.bit_count() ==1 :
                board[i,j]=int(math.log2(Variable.objects[i*9+j].domain<<1))
'''
def get_domain(d:int) -> int:
    l=0
    for i in range(1,10) :
        if d % 2 == 1 :
            l=l*10+i
        d=d//2
    return l
def tree_arcs (array : np.ndarray) :
    board=array.copy()
    for i in range (9) :
        for j in range (9):
            if array[i,j] == 0:
                if Variable.objects[i*9+j].domain.bit_count() > 1 :
                    board[i,j]=get_domain(Variable.objects[i*9+j].domain)
    return board

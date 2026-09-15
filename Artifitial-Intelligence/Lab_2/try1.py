import numpy as np


def num_of_connected_4(line, flag2):
    num_of_connected4 = 0
    l = len(line)
    for i in range(l - 3):
        subline = line[i: i + 4]
        flag = True
        for j in range(4):
            if subline[j] != flag2:
                flag = False
                break
        if flag:
            num_of_connected4 += 1
    return num_of_connected4


def check_connected_4_in_rows(board: np.ndarray, flag, flag2):
    num_of_connected = 0
    for i in range(6):
        if flag2:
            num_of_connected += num_of_connected_4(board[i, :], flag)
        else:
            num_of_connected += number_of_connected_3(board[i, :], flag)
    return num_of_connected


def check_connected_4_in_columns(board: np.ndarray, flag, flag2):
    num_of_connected = 0
    for i in range(7):
        if flag2:
            num_of_connected += num_of_connected_4(board[:, i], flag)
        else:
            num_of_connected += number_of_connected_3(board[:, i], flag)
    return num_of_connected


def get_diagonals_from_board(board: np.ndarray):
    diagonals = []
    for i in range(-2, 4):
        diagonals.append(np.diag(board, k=i))
    board = np.fliplr(board)
    for j in range(-2, 4):
        diagonals.append(np.diag(board, k=j))
    return diagonals


def number_of_connected_4_human(board: np.ndarray):
    return check_connected_4_in_columns(board, -1, True) + check_connected_4_in_rows(board, -1,
                                                                                     True) + check_connected_4_in_diagolans(
        board, -1, True)


def number_of_connected_4_computer(board: np.ndarray):
    return check_connected_4_in_columns(board, 1, True) + check_connected_4_in_rows(board, 1,
                                                                                    True) + check_connected_4_in_diagolans(
        board, 1, True)


def number_of_connected_3_human(board: np.ndarray):
    return check_connected_4_in_columns(board, -1, False) + check_connected_4_in_rows(board, -1,
                                                                                      False) + check_connected_4_in_diagolans(
        board, -1, False)


def number_of_connected_3_computer(board: np.ndarray):
    return check_connected_4_in_columns(board, 1, False) + check_connected_4_in_rows(board, 1,
                                                                                     False) + check_connected_4_in_diagolans(
        board, 1, False)


def check_connected_4_in_diagolans(board: np.ndarray, flag, flag2):
    num_of_connected = 0
    for i in range(12):
        if flag2:
            num_of_connected += num_of_connected_4(get_diagonals_from_board(board)[i], flag)
        else:
            num_of_connected += number_of_connected_3(get_diagonals_from_board(board)[i], flag)
    return num_of_connected


def number_of_connected_3(line, flag2):
    num_of_connected3 = 0
    before = -10
    after = -10
    ln = len(line)
    for i in range(ln - 2):
        subline = line[i:i + 3]
        flag = True
        if i == 0:
            before = -10
        else:
            before = line[i - 1]
        if i == ln - 3:
            after = -10
        else:
            after = line[i + 3]
        for j in range(3):
            if subline[j] != flag2:
                flag = False
                break
        if flag and before == 0:
            num_of_connected3 += 1
        if flag and after == 0:
            num_of_connected3 += 1
    return num_of_connected3


def center_control(board, flag):
    num_of_centers = 0
    num_of_left = 0
    num_of_right = 0

    for i in range(6):
        if board[i][3] == flag:
            num_of_centers += 1

    for i in range(6):
        if board[i][2] == flag:
            num_of_left += 1

    for i in range(6):
        if board[i][4] == flag:
            num_of_right += 1

    return num_of_centers, num_of_left, num_of_right










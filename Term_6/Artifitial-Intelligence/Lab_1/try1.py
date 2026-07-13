def get_children ( board : int ) :
    board_str = str(board).zfill(9)
    zero_index = search_for_zero(board)
    right = True
    left = True
    up = True
    down = True

    if (zero_index+1) % 3 == 0 :
        right = False
    if (zero_index+1) % 3 == 1 :
        left = False
    if zero_index < 3 :
        up = False
    if zero_index > 5 :
        down = False

    arr_of_children = []
    if right :
       # board_str = str(board)
        temp = list(board_str)
        temp[zero_index] , temp[zero_index+1] = temp[zero_index+1] , temp[zero_index]
       # board_str = "".join(temp)
        arr_of_children.append(int("".join(temp)))
    if left :
        #board_str = str(board)
        temp = list(board_str)
        temp[zero_index] , temp[zero_index-1] = temp[zero_index-1] , temp[zero_index]
        #board_str = "".join(temp)
        arr_of_children.append(int("".join(temp)))

    if up :
       # board_str = str(board)
        temp = list(board_str)
        temp[zero_index] , temp[zero_index-3] = temp[zero_index-3] , temp[zero_index]
        #board_str = "".join(temp)
        arr_of_children.append(int("".join(temp)))

    if down :
        #board_str = str(board)
        temp = list(board_str)
        temp[zero_index] , temp[zero_index+3] = temp[zero_index+3] , temp[zero_index]
        #board_str = "".join(temp)
        arr_of_children.append(int("".join(temp)))

    return arr_of_children

def search_for_zero ( board : int ) :
    board_str = str(board)
    for i in range(len(board_str)) :
        if board_str[i] == '0' :
            return i
    return 0
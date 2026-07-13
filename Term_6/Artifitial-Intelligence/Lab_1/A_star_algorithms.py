import math

goal_locations = {
    1: (0,1),
    2: (0,2),
    3: (1,0),
    4: (1,1),
    5: (1,2),
    6: (2,0),
    7: (2,1),
    8: (2,2)
}

def manhattan ( current_state : int ) :
    h = 0
    for k in range ( 1 , 9 ) :
        current_location = location_2D_index(location_1D_index(current_state , k ))
        diff_x = current_location[0] - goal_locations[k][0]
        diff_y = current_location[1] - goal_locations[k][1]
        h += abs(diff_x) + abs(diff_y)

    return h

def euclidean ( current_state : int ) :
    h = 0
    for k in range ( 1 , 9 ) :
        current_location = location_2D_index(location_1D_index(current_state , k ))
        diff_x = current_location[0] - goal_locations[k][0]
        diff_y = current_location[1] - goal_locations[k][1]
        h += math.sqrt( (diff_x**2) + (diff_y**2) )

    return h

def location_1D_index ( board : int , num : int ) :
    board_str = fixed_string(board)
    for i in range(len(board_str)) :
        if board_str[i] == str(num) :
            return i
    return None

def location_2D_index ( index : int ) :
    x = None
    y = None

    if index < 3 : x = 0
    elif 2 < index < 6 : x = 1
    else : x = 2

    if index % 3 == 0 : y = 0
    elif index % 3 == 1 : y = 1
    else : y = 2

    return x,y

def fixed_string ( num : int ) :
    str_num = str(num)
    if len(str_num) == 8 :
        return '0' + str_num
    else :
        return str_num
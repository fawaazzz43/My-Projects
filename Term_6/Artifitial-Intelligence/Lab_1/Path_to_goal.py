import try1
from try2 import node
def path_to_goal(node : node) :
    path = []
    path2 = []
    while node.parent is not None :
        path2.append(node.data)
        parent = node.parent
        path.append(get_direction(node.data, parent.data))
        node = parent
    path2.append(node.data)
    
    return path[::-1],len(path)

def get_direction(child, parent) :

    zero_index = try1.search_for_zero(child)
    parent_zero_index = try1.search_for_zero(parent)
    direction = zero_index - parent_zero_index

    if direction == 1:
        return "Right"
    elif direction == -1:
        return "Left"
    elif direction == 3:
        return "Down"
    elif direction == -3:
        return "Up"
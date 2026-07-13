from node import *
from node import node



visited=dict()
def minimizeAB(state: node, A, B, k, hight,tree) -> list:
    tree[hight].append(state)
    global num_of_node_expanded
    num_of_node_expanded += 1
    if k == hight:
        rep = 0
        flat = state.board.ravel()   
        for x in flat:           
            rep = rep * 3 + int(x) + 1
        if rep not in visited :
            state.value=state.heurestic_function()
            visited[rep]=state.value
            return [None, state.value]
        else :
            return [None, visited[rep]]
    minchild = [None, float('inf')]
    state.children = state.get_children(-1)
    if state.children == [] :
        state.value=state.heurestic_function()
        return [None, state.value]
    for child in state.children:
        return_from_max = maximizeAB(child, A, B, k, hight + 1,tree)
        if return_from_max[1] < minchild[1]:
            minchild = [child, return_from_max[1]]
        if minchild[1] <= A:
            break
        if minchild[1] < B:
            B = minchild[1]
    state.value=minchild[1]        
    return minchild


def maximizeAB(state: node, A, B, k, hight,tree) -> list:
    tree[hight].append(state)
    global num_of_node_expanded
    num_of_node_expanded += 1
    if k == hight:
        rep = 0
        flat = state.board.ravel()   
        for x in flat:         
            rep = rep * 3 + int(x) + 1
        if rep not in visited :
            state.value=state.heurestic_function()
            visited[rep]=state.value
            return [None, state.value]
        else :
            return [None, visited[rep]]
    maxchild = [None, float('-inf')]
    state.children = state.get_children(1)
    if state.children == [] :
        state.value=state.heurestic_function()
        return [None, state.value]
    for child in state.children:
        return_from_min = minimizeAB(child, A, B, k, hight + 1,tree)
        if return_from_min[1] > maxchild[1]:
            maxchild = [child, return_from_min[1]]
        if maxchild[1] >= B:
            break
        if maxchild[1] > A:
            A = maxchild[1]
    state.value=maxchild[1]        
    return maxchild


def decision(state: node, k, hight):
    global num_of_node_expanded
    num_of_node_expanded = 0
    visited=dict()
    tree = dict()
    for i in range(k+1):
        tree[i] = []
    maxchild = maximizeAB(state, float('-inf'), float('inf'), k, 0,tree)
    return maxchild[0],tree, num_of_node_expanded


def maximize(node, depth, tree,hight):
    tree[hight].append(node)
    global num_of_node_expanded
    num_of_node_expanded += 1
    if depth == 0:
        rep = 0
        flat = node.board.ravel()   
        for x in flat:         
            rep = rep * 3 + int(x) + 1
        if rep not in visited :
            node.value=node.heurestic_function()
            visited[rep]=node.value

            return None, node.value
        
        else :
            return None, visited[rep]
    max_score = -float('inf')
    best_move = None
    node.children = node.get_children(1)
    if node.children == [] :
        node.value=node.heurestic_function()
        return None, node.value
    for child in node.children:
        x, score = minimize(child, depth - 1,tree,hight+1)
        if score > max_score:
            max_score = score
            best_move = child
    node.value=max_score
    return best_move, max_score


def minimize(node, depth, tree,hight):
    tree[hight].append(node)
    global num_of_node_expanded
    num_of_node_expanded += 1
    if depth == 0:
        rep = 0
        flat = node.board.ravel()   
        for x in flat:         
            rep = rep * 3 + int(x) + 1
        if rep not in visited :
            node.value=node.heurestic_function()
            visited[rep]=node.value
            return None, node.value
        else :
            return None, visited[rep]
    min_score = float('inf')
    best_move = None
    node.children = node.get_children(-1)
    if node.children == [] :
        node.value=node.heurestic_function()
        return None, node.value
    for child in node.children:
        x, score = maximize(child, depth - 1,tree,hight+1)
        if score < min_score:
            min_score = score
            best_move = child
    node.value=min_score        
    return best_move, min_score


def minmax_algorithm(board, depth):
    global num_of_node_expanded
    num_of_node_expanded = 0
    visited=dict()
    tree = dict()
    for i in range(depth+1):
        tree[i] = []
    best_move, score = maximize(board, depth,tree,0)
    
    return best_move,tree, num_of_node_expanded

def expectiminimax ( node , depth , type , col ,tree,hight) :

    tree[hight].append(node)
    global num_of_node_expanded
    num_of_node_expanded += 1
    if depth == 0:
        rep = 0
        flat = node.board.ravel()
        for x in flat:
            rep = rep * 3 + int(x) + 1
        if rep not in visited :
            node.value=node.heurestic_function()
            visited[rep]=node.value
            return node.heurestic_function() , None
        else :
         return visited[rep] , None
    if type == "MAX":
        best_value = float('-inf')
        best_child = None
        node.children = node.get_children(1)
        if node.children == [] :
             node.value=node.heurestic_function()
             return node.value, None
        for child in node.children:
            value , _ = expectiminimax( child , depth - 1 , "MIN" , col, tree , hight + 1 )

            if value > best_value:
                best_value = value
                best_child = child
        node.value=best_value        
        return best_value , best_child
    elif type == "MIN":
        best_value = float('inf')
        best_child = None
        node.children = node.get_children(-1)
        if node.children == [] :
             node.value=node.heurestic_function()
             return node.value, None
        for child in node.children:
            value , _ = expectiminimax ( child , depth - 1 , "MAX" , col, tree, hight + 1 )
            if value < best_value:
                best_value = value
                best_child = child
        node.value=best_value        
        return best_value , best_child
    else :
        value = 0
        for outcome , prob in calculate_probabilities( node , col ).items() :
            val , _ = expectiminimax ( outcome , depth - 1 , type , col, tree, hight + 1 )
            value += prob * val
        return value , None


def calculate_probabilities(state, chosen_col):

    results = {}
    index = state.get_index(chosen_col)
    left_col = state.board[:,index - 1]
    right_col = state.board[:,index + 1]


    has_left = state.is_valid_column(left_col)
    has_right = state.is_valid_column(right_col)

    if has_left and has_right:
        results[chosen_col] = 0.6
        results[left_col] = 0.2
        results[right_col] = 0.2
    elif has_left :
        results[chosen_col] = 0.6
        results[left_col] = 0.4
    elif has_right :
        results[chosen_col] = 0.6
        results[right_col] = 0.4
    else :
        results[chosen_col] = 1

    return results


def expectiminimax_decision(state: node, depth,col):
    global num_of_node_expanded
    num_of_node_expanded = 0
    visited=dict()
    tree = dict()
    for i in range(depth+1):
        tree[i] = []
    _ ,best_child = expectiminimax(state, depth, "MAX", col, tree, 0)
    return best_child, tree, num_of_node_expanded       
       
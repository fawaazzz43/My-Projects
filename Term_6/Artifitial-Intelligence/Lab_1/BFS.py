import time
import try1
from try2 import node
import Path_to_goal


def BFS ( board : node ) :
    
    frontier = []
    end_time=0
    set_states = set()
    frontier.append(board)
    set_states.add(board.data)

    explored = set()

    num_of_nodes_expanded = 0
    #search_depth = 0
    flag = 0
    #last_child = try1.get_children(board.data)[-1]
    level_stack= []
    level_stack.append(0)
    level = 0
    max_level = 0

    start_time = time.time()
    while frontier : 
        state = frontier.pop(0)
        level = level_stack.pop()
        set_states.remove(state.data)
        children = try1.get_children(state.data)
        print(state.data)
        explored.add(state.data)
        level =level+1
        if level > max_level :
            max_level = level
            

        if state.data == 12345678 :
            flag = 1
            end_time = time.time()
            break
        else :
            for child in children :
                if child not in set_states and child not in explored :
                    frontier.append(node(child, state))
                    set_states.add(child)
                    level_stack.append(level)
                #if child == last_child :
                    #search_depth += 1
                    #last_child = try1.get_children(child)[-1]
                
        num_of_nodes_expanded += 1

    time_taken = end_time - start_time
    
    if flag :
        return True , num_of_nodes_expanded , max_level , time_taken,state
    else :
        return False , num_of_nodes_expanded , max_level , 0 , None

'''found, nodes_expanded, depth, time_taken , goal= BFS(node(120345678, None))
if found :
    print("Goal Found")
    print("Number of nodes expanded: ", nodes_expanded)
    print("Search depth: ", depth)
    print("Time taken: ", time_taken, " seconds")
    print("Path to Goal: ", Path_to_goal.path_to_goal(goal))
else :
    print("goal not found")'''


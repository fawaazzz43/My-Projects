import try1
import time
from try2 import node



def DFS_algorithm(board : node) :

    stack = []
    stack_states=set()
    stack.append(board)
    stack_states.add(board.data)

    visited = set()
    level_stack= []
    level_stack.append(0)
    level = 0
    max_level = 0
    
    
    
    start = time.time()
    
    while stack :
        state = stack.pop()
        level = level_stack.pop()
        
        stack_states.remove(state.data)
       
       
        if state.data == 12345678 :
            end = time.time()
            print("Goal Found")
            print("Time taken: ", end - start, " seconds")
            
            visited.add(state.data)
            print("Search depth: ", max_level)
            return True,state,visited,max_level,end - start
        
        
        print(state.data)
        visited.add(state.data)
        children = try1.get_children(state.data)

        level =level+1
        if level > max_level :
            max_level = level

        for child in children :

            if child not in visited and child not in stack_states :

                stack.append(node(child, state))
                stack_states.add(child)
                level_stack.append(level)
    
    return False,None,visited,0,0






#load
'''DFS,goal, visited,level = DFS_algorithm(board)
if DFS :
    path,l=Path_to_goal.path_to_goal(goal)
    print("Path to Goal: ", path)
    print("Number of nodes expanded: ", len(visited))
else :
    print("goal not found")'''
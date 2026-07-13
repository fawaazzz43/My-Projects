import try1
import time
import Path_to_goal
#board = 123405678
def Iterative_DFS(start : int) :
    max_level=0
    start_time=time.time()
    stack=[]
    stack_states=set()
    stack.append(start*100)
    stack_states.add(start)
    levels=dict()
    
    num_of_nodes_expanded = 0
    level=0
    limit=0
    visited=set()
    i=0
   
    while num_of_nodes_expanded <= 362880:
        if limit == 100 :
            exit(0)
        if stack :
            current_state=stack.pop()
            level=current_state%100
            current_state=current_state // 100
            stack_states.remove(current_state)
            
            i+=1
        else :
            stack.clear()
            stack_states.clear()
            visited.clear()
            levels.clear()
            limit+=1
            num_of_nodes_expanded=0
            level=0
            stack.append(start*100)
            stack_states.add(start)
            continue

        
        print(current_state)
        
        
        if (current_state) == 12345678 :
            visited.add(current_state)
            num_of_nodes_expanded+=1
            if level not in levels:
                levels[level] = []
                levels[level] = [current_state]
            else:
                levels[level].append(current_state)
            end=time.time()
            print("Goal Found")
            print("Time taken: ", end - start_time, " seconds")
            print("Number of nodes expanded: ",num_of_nodes_expanded)
            print("Search depth : ", max_level)
            print("Cost of the path: " ,level)
            return True,levels,num_of_nodes_expanded,max_level,end - start_time,level
        if level == limit :
            if stack :
                visited.add(current_state)
                num_of_nodes_expanded+=1
                if level not in levels:
                    levels[level] = []
                    levels[level] = [current_state]
                else:
                    levels[level].append(current_state)
                    
                
                continue
            else :
                stack.clear()
                stack_states.clear()
                visited.clear()
                levels.clear()
                limit+=1
                num_of_nodes_expanded=0
                level=0
                stack.append(start*100)
                stack_states.add(start)
                continue
        
        visited.add(current_state)
        num_of_nodes_expanded+=1
        if level not in levels:
            levels[level] = []
            levels[level] = [current_state]
        else:
            levels[level].append(current_state)
        
        children=try1.get_children(current_state)
        level+=1
        if level > max_level :
            max_level=level

        
        for child in children :
            if child not in visited and child not in stack_states:
                stack.append(child*100+level)
                stack_states.add(child)
    return False,None,num_of_nodes_expanded,max_level,0,level



def path_to_goal(levels:dict,level_goal :int):
    path = []
    path2 = []
    state=levels[level_goal].pop()
    while level_goal > 0 :
       
        path2.append(state)
        level_parent = level_goal-1
        parent=levels[level_parent].pop()
        
        path.append(Path_to_goal.get_direction(state, parent))
        state=parent
        level_goal-=1
        
    path2.append(state)
    print("Path to Goal: ", path2[::-1])
    return path[::-1],len(path)
def IDS(start : int):
    found, levels,num_of_nodes_expanded,max_level,time,level=Iterative_DFS(start)
    if found:
        path,cost=path_to_goal(levels,level)
        return found, num_of_nodes_expanded, max_level, time,cost,path
    else :
        return found, num_of_nodes_expanded, max_level, time,0,None

'''found, levels,num_of_nodes_expanded,max_level,time_run,level=Iterative_DFS(120345678)
if found :
    print(path_to_goal(levels,level))
    print("Cost of the path: " ,level)
    return True,levels,num_of_nodes_expanded,max_level,end - start_time,level'''
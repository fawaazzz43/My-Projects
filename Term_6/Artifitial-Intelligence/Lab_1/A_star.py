import heapq
import time

import A_star_algorithms
import Path_to_goal
import try1
from try2 import node


def A_star_manhattan(state: node):
    end_time=0
    frontier = []
    counter = 0

    heapq.heappush(frontier,(A_star_algorithms.manhattan(state.data) + state.cost, id(state), state))

    explored = set()

    nodes_expanded = 0
    flag = 0
    search_depth = 0
    start_time = time.time()

    while len(frontier) != 0:

        priority, _ , current = heapq.heappop(frontier)
        print(current.data)
        explored.add(current.data)

        if current.data == 12345678:

            search_depth = current.cost
            flag = 1
            end_time = time.time()

            arr_of_path = Path_to_goal.path_to_goal(current)

            break

        else:

            for child_data in try1.get_children(current.data):

                child = node(child_data, current, current.cost + 1)

                cost = child.cost + A_star_algorithms.manhattan(child.data)

                if child.data not in explored:

                    heapq.heappush(frontier, (cost, id(child), child))

        nodes_expanded += 1

    time_taken = end_time - start_time

    if flag:
        return True, nodes_expanded, search_depth, time_taken, arr_of_path, len(arr_of_path)
    else:
        return False, nodes_expanded, search_depth, time_taken , None , 0





def A_star_euclidean(state: node):
    end_time=0
    frontier = []
    counter = 0

    heapq.heappush(frontier,(A_star_algorithms.euclidean(state.data) + state.cost, id(state), state))

    explored = set()

    nodes_expanded = 0
    flag = 0
    search_depth = 0
    start_time = time.time()

    while len(frontier) != 0:

        priority, _ , current = heapq.heappop(frontier)
        print(current.data)
        explored.add(current.data)

        if current.data == 12345678:

            search_depth = current.cost
            flag = 1
            end_time = time.time()

            arr_of_path = Path_to_goal.path_to_goal(current)

            break

        else:

            for child_data in try1.get_children(current.data):

                child = node(child_data, current, current.cost + 1)

                cost = child.cost + A_star_algorithms.euclidean(child.data)

                if child.data not in explored:

                    heapq.heappush(frontier, (cost, id(child), child))

        nodes_expanded += 1

    time_taken = end_time - start_time

    if flag:
        return True, nodes_expanded, search_depth, time_taken, arr_of_path, len(arr_of_path)
    else:
        return False, nodes_expanded, search_depth, 0,None,0


# state_data = 120345678
# state = node ( state_data , None , 0 )
#
# print( A_star_euclidean(state) )
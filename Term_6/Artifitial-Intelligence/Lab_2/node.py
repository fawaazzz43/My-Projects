from try1 import *
import numpy
import heurestic_function as hf
class node:
    def __init__(self, board: numpy.ndarray):
        self.board = board
        self.children = []
        self.value =float('-inf')

    def heurestic_function(self):
        
        binary_ai , binary_hum=hf.to_binary(self.board)
    
        number_of_connected_4_hu, number_of_connected_4_double_hu = hf.number_of_connected_4_binary(binary_hum,binary_ai)
        number_of_connected_3_hu, number_of_connected_3_double_hu,number_of_3_doubleinmid_hu = hf.number_of_connected_3_binary(binary_hum,binary_ai)
        human_score = number_of_connected_4_hu * 15000 +number_of_connected_4_double_hu*12000+ number_of_connected_3_hu * 600 +number_of_connected_3_double_hu*4500+ number_of_3_doubleinmid_hu*1800+ hf.number_center(binary_hum)*30
         
        number_of_connected_3_ai, number_of_connected_3_double_ai,number_of_3_doubleinmid_ai = hf.number_of_connected_3_binary(binary_ai,binary_hum)
        number_of_connected_4_ai, number_of_connected_4_double_ai = hf.number_of_connected_4_binary(binary_ai,binary_hum)
        computer_score = number_of_connected_4_ai * 15000 +number_of_connected_4_double_ai*12000+ number_of_connected_3_ai * 500 + number_of_connected_3_double_ai*4000+ number_of_3_doubleinmid_ai*1500+ hf.number_center(binary_ai)*30
        score = computer_score - human_score

        return score

    def get_children(self,player):
        children = []
        indexes=[3,2,4,1,5,0,6]
        for i in indexes:
            if self.board[0, i] != 1 and self.board[0, i] != -1:
                board_copy = self.board.copy()
                index = self.get_index_of_last_disc(self.board[:, i])
                board_copy[index, i] = player
                children.append(node(board_copy))
        return children

    def get_col (self,player) :
        children = self.get_children(player)
        max = 0
        index = -1
        for child in children:
            if child.heurestic_function() > max :
                max = child.heurestic_function()
                index = children.index(child)
        return self.board[:, index]

    def get_next_child ( self , col ) :
        index = self.get_index_of_last_disc(col)
        board_copy = self.board.copy()
        board_copy[index, col] = 1

        return node(board_copy)

    def get_index_of_last_disc(self, col):
        for i in range(5, -1, -1):
            if col[i] == 0:
                return i
        return -1

    def get_available_columns(self):
        available_columns = []
        index= []
        for i in range(7):
            if self.board[0, i] == 0:
                available_columns.append(self.board[:, i])
                index.append(i)
        return available_columns , index

    def apply_move(self, col):
        row = self.get_index_of_last_disc(col)
        self.board[row, col] = 1
        return self

    def is_valid_column ( self , col ) :
        i = 0
        while i < 7 :
            if numpy.array_equal(col,self.board[:, i]) :
                return True
            i += 1
        return False

    def get_index (self , col) :
        i = 0
        while i < 7 :
            if numpy.array_equal(col,self.board[:, i]) :
                return i
            i += 1
        return -1
    

    
# '''def heurestic_function(self):
#         centers, left, right = center_control(self.board, -1)

#         human_score = number_of_connected_4_human(self.board) * 1000 + number_of_connected_3_human(
#             self.board) * 100 + centers * 10 + left * 7.5 + right * 7.5

#         centres, left, right = center_control(self.board, 1)

#         computer_score = number_of_connected_4_computer(self.board) * 1000 + number_of_connected_3_computer(
#             self.board) * 100 + centres * 10 + left * 7.5 + right * 7.5

#         score = computer_score - human_score

#         return score'''
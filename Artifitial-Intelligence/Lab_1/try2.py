class node :
    def  __init__ (self, data : int, parent : "node", cost : int=0):
        self.data = data
        self.parent = parent
        self.cost = cost     
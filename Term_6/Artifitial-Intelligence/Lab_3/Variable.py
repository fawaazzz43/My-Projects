class Variable:
    objects: dict[int, 'Variable'] = {}

    def __init__(self, domain: int, index):
        self.domain = domain
        self.index = index

    @staticmethod
    def unique_variable(index: int, domain: int):
        if index not in Variable.objects:
            Variable.objects[index] = Variable(domain, index)
        return Variable.objects[index]
# %%
import random


class Leaf:
    def __init__(self, value):
        self.parent = None
        self.r_son = None
        self.l_son = None
        self.color = 'R'
        self.value = value
        self.insert_left = None

    def switch_insert(self):
        self.insert_left = not self.insert_left

    def __repr__(self):
        return f'{self.color}{self.value}' if self.value is not None else 'NIL'


class BST:
    def __init__(self):
        self.NIL = Leaf(None)
        self.NIL.color = 'B'
        self.root = self.NIL

    def search(self, k, x=None):
        if x is None:
            return self.search(k, self.root)
        while x is not self.NIL and k != x.value:
            if k < x.value:
                x = x.l_son
            else:
                x = x.r_son
        return x

    def insert(self, z):
        x = self.root
        y = self.NIL
        z.l_son = z.r_son = y
        while x is not self.NIL:
            y = x
            if z.value < x.value:
                x = x.l_son
            elif z.value > x.value:
                x = x.r_son
            else:
                x = x.l_son if x.insert_left else x.r_son
        z.parent = y
        if y is self.NIL:
            self.root = z
        else:
            if z.value < y.value:
                y.l_son = z
            elif z.value > y.value:
                y.r_son = z
            else:
                if z.insert_left:
                    y.l_son = z
                else:
                    y.r_son = z
                while y is not self.NIL:
                    if y.value == z.value:
                        y.switch_insert()
                    y = y.parent

    def delete(self, z):
        if z.l_son is self.NIL:
            self.transplant(z, z.r_son)
        elif z.r_son is self.NIL:
            self.transplant(z, z.l_son)
        else:
            y = self.minimum(z.r_son)
            if y.parent is not z:
                self.transplant(y, y.r_son)
                y.r_son = z.r_son
                y.r_son.parent = y
            self.transplant(z, y)
            y.l_son = z.l_son
            y.l_son.parent = y

    def transplant(self, u, v):
        if u.parent is self.NIL:
            self.root = v
        else:
            if u is u.parent.l_son:
                u.parent.l_son = v
            else:
                u.parent.r_son = v
            if v is not self.NIL:
                v.parent = u.parent

    def minimum(self, x):
        while x.l_son is not self.NIL:
            x = x.l_son
        return x

    def print_tree(self, print_NIL=False):
        self._print(self.root, -1, print_NIL)

    def _print(self, leaf, tabs, print_NIL):
        tabs += 1
        if print_NIL and leaf is None:
            return
        elif not print_NIL and leaf is self.NIL:
            return
        self._print(leaf.l_son, tabs, print_NIL)
        for i in range(tabs):
            print('_ ', end='')
        print(leaf)
        self._print(leaf.r_son, tabs, print_NIL)


class RBT(BST):
    def __init__(self):
        super().__init__()

    def insert(self, x):
        super().insert(x)
        while x is not self.root and x.parent.color == 'R':
            if x.parent is x.parent.parent.l_son:
                y = x.parent.parent.r_son
                # przypadek 1 -- brat ojca x jest czerwony = ojciec i jego brat stają się czerwoni
                if y.color == 'R':
                    x.parent.color = 'B'
                    y.color = 'B'
                    x.parent.parent.color = 'R'
                    x = x.parent.parent
                # przypadek 2 -- brat ojca x jest czarny, x i jego ojciec leżą w "różnych kierunkach"
                # czyli jeżeli ojciec jest prawym synem swojego ojca, a syn jest lewym synem ojca, albo na odwrót =
                # doprowadzamy do przypadku 3
                elif x is x.parent.r_son:
                    x = x.parent
                    self.l_rotate(x)
                # przypadek 3 -- brat ojca x jest czarny, x i jego ojciec leżą w "tym samym kierunku" =
                # ojciec staje się czarny, jego ojciec czerwony, obracamy w prawo
                else:
                    x.parent.color = 'B'
                    x.parent.parent.color = 'R'
                    self.r_rotate(x.parent.parent)
            else:
                y = x.parent.parent.l_son
                if y.color == 'R':
                    x.parent.color = 'B'
                    y.color = 'B'
                    x.parent.parent.color = 'R'
                    x = x.parent.parent
                elif x is x.parent.l_son:
                    x = x.parent
                    self.r_rotate(x)
                else:
                    x.parent.color = 'B'
                    x.parent.parent.color = 'R'
                    self.l_rotate(x.parent.parent)
        self.root.color = self.NIL.color = 'B'

    def l_rotate(self, x):
        y = x.r_son
        x.r_son = y.l_son
        if y.l_son is not self.NIL:
            y.l_son.parent = x
        y.parent = x.parent
        if x.parent is self.NIL:
            self.root = y
        elif x is x.parent.l_son:
            x.parent.l_son = y
        else:
            x.parent.r_son = y
        y.l_son = x
        x.parent = y

    def r_rotate(self, x):
        y = x.l_son
        x.l_son = y.r_son
        if y.r_son is not self.NIL:
            y.r_son.parent = x
        y.parent = x.parent
        if x.parent is self.NIL:
            self.root = y
        elif x is x.parent.r_son:
            x.parent.r_son = y
        else:
            x.parent.l_son = y
        y.r_son = x
        x.parent = y

    def min_height(self):
        return self._height(self.root, min)

    def max_height(self):
        return self._height(self.root, max)

    def red_nodes(self):
        return self._red(self.root, 0)

    def _red(self, x, count):
        if x is self.NIL:
            return count
        if x.color == 'R':
            return self._red(x.l_son, 0) + self._red(x.r_son, 0) + 1
        return self._red(x.l_son, count) + self._red(x.r_son, count)

    def _height(self, x, func):
        if x is self.NIL:
            return 0
        return func(self._height(x.l_son, func), self._height(x.r_son, func)) + 1


if __name__ == "__main__":
    leafs = [Leaf(x)
             for x in [38, 31, 22, 8, 20, 5, 10, 9, 21, 27, 29, 25, 28]]
    tree = RBT()

    for i in leafs:
        print(f'insert {i.value}')
        tree.insert(i)
        tree.print_tree()
        print('-' * 70)

    print('Ilosc czerwonych lisci w drzewie =', tree.red_nodes())
    print('Maksymalna wysokosc drzewa =', tree.max_height())
    print('Minimalna wysokosc drzewa =', tree.min_height())

    tree2 = RBT()
    for _ in range(1001):
        tree2.insert(Leaf(random.randint(0, 2**12)))

    print('-' * 70)

    print('Ilosc czerwonych lisci w drzewie =', tree2.red_nodes())
    print('Maksymalna wysokosc drzewa =', tree2.max_height())
    print('Minimalna wysokosc drzewa =', tree2.min_height())

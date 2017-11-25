

def cost_func(state):
    cost = 0
    for slot in state:
        teacher_count = [0] * (10+1)
        class_count = [0] * (10+1)
        room_count = [0] * (10+1)

        for req in slot:
            teacher_count[req[0]] = teacher_count[req[0]]+1
            class_count[req[1]] = class_count[req[1]]+1
            room_count[req[2]] = room_count[req[2]]+1
        for count in teacher_count:
            cost = cost + max(count - 1, 0)*1
        for count in class_count:
            cost = cost + max(count - 1, 0)*1
        for count in room_count:
            cost = cost + max(count - 1, 0)*1

    return cost


state = [[(3, 1, 2)], [(4, 3, 1), (4, 1, 4), (2, 1, 3), (2, 1, 4)], [(3, 2, 3)], [(4, 2, 4), (1, 3, 4), (2, 3, 2)], [(3, 4, 1)], [(3, 4, 3)], [(1, 4, 1), (4, 1, 3), (4, 3, 4), (2, 4, 1)], [(4, 4, 2), (3, 3, 2), (1, 4, 4), (4, 2, 4), (2, 4, 3), (1, 1, 4), (4, 1, 2)], [(4, 1, 1), (2, 4, 2)], [(1, 4, 3), (4, 2, 2)], [(2, 1, 1), (1, 3, 3)], [(4, 4, 1), (3, 2, 2)], [(2, 1, 2), (1, 4, 3), (1, 3, 1), (2, 2, 4), (3, 2, 4)], [(2, 2, 2)], [(1, 3, 2), (4, 3, 1), (2, 4, 1), (1, 1, 3), (3, 4, 1), (4, 3, 1), (2, 3, 1)], [(3, 2, 3), (4, 4, 2)], [(3, 3, 4)], [(1, 1, 4)], [(4, 2, 1), (4, 3, 1)], [(1, 3, 4), (4, 4, 4), (2, 3, 3), (4, 4, 1)], [(1, 4, 3)], [(3, 3, 4), (4, 2, 4), (3, 4, 4), (4, 2, 3)], [(2, 2, 1), (3, 2, 3), (4, 2, 2), (3, 3, 1), (1, 4, 2)], [(3, 2, 3), (2, 3, 4), (1, 1, 2), (4, 3, 1)], [(2, 3, 4), (1, 4, 3), (2, 1, 2)], [(2, 3, 4), (2, 1, 1)], [(1, 4, 4)], [(1, 1, 1), (4, 1, 2), (2, 2, 2), (3, 1, 1)], [(3, 2, 2), (1, 4, 3)], [(3, 2, 1), (1, 3, 4)], [(2, 1, 2), (2, 2, 2), (3, 4, 2), (1, 2, 4)], [(1, 4, 1), (4, 2, 4), (2, 1, 2), (3, 1, 4), (3, 3, 3), (4, 1, 3), (3, 4, 3), (2, 2, 4)], [(3, 4, 1), (1, 1, 1), (3, 3, 3), (2, 2, 4), (2, 2, 4), (4, 1, 1)], [(1, 4, 3), (4, 2, 1)], [(3, 3, 3), (1, 1, 4), (3, 1, 3), (3, 3, 3), (2, 2, 2)], [(2, 4, 2)], [(1, 1, 2), (3, 1, 4), (3, 2, 3), (4, 3, 3)], [(1, 4, 2), (1, 2, 1), (2, 1, 2), (1, 3, 3)], [(2, 3, 2)], [(1, 1, 3), (4, 4, 3), (4, 3, 1), (3, 2, 2)]]
print(cost_func(state))
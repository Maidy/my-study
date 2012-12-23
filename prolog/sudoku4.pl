valid([]).
valid([Head|Tail]) :-
    fd_all_different(Head),
    valid(Tail).

sudoku(Puzzle, Solution) :-
    Solution = Puzzle,
    Puzzle = [S11, S12, S13, S14,
              S21, S22, S23, S24,
              S31, S32, S33, S34,
              S41, S42, S43, S44],
    fd_domain(Puzzle, 1, 4),

    R1 = [S11, S12, S13, S14],
    R2 = [S21, S22, S23, S24],
    R3 = [S31, S32, S33, S34],
    R4 = [S41, S42, S43, S44],

    C1 = [S11, S21, S31, S41],
    C2 = [S12, S22, S32, S42],
    C3 = [S13, S23, S33, S43],
    C4 = [S14, S24, S34, S44],

    S1 = [S11, S12, S21, S22],
    S2 = [S13, S14, S23, S24],
    S3 = [S31, S32, S41, S42],
    S4 = [S33, S34, S43, S44],

    valid([R1, R2, R3, R4,
           C1, C2, C3, C4,
           S1, S2, S3, S4]).

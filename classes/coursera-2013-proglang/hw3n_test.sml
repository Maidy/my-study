only_capitals ["Abc", "abc", "cat", "Boy"];

longest_string1 []; (* "" *)
longest_string1 ["abc", "bc"];  (* "abc" *)
longest_string1 ["abc", "bc", "defg"]; (* "defg" *)
longest_string1 ["abc", "bc", "def"];  (* "abc" *)

longest_string2 []; (* "" *)
longest_string2 ["abc", "bc"];  (* "abc" *)
longest_string2 ["abc", "bc", "defg"]; (* "defg" *)
longest_string2 ["abc", "bc", "def"];  (* "def" *)

longest_string3 []; (* "" *)
longest_string3 ["abc", "bc"];  (* "abc" *)
longest_string3 ["abc", "bc", "defg"]; (* "defg" *)
longest_string3 ["abc", "bc", "def"];  (* "abc" *)

longest_string4 []; (* "" *)
longest_string4 ["abc", "bc"];  (* "abc" *)
longest_string4 ["abc", "bc", "defg"]; (* "defg" *)
longest_string4 ["abc", "bc", "def"];  (* "def" *)

longest_capitalized ["abc", "Abc", "def", "Sif", "xxxxa"]; (* "Abc" *)

rev_string "abcde"; (* "edcba" *)

first_answer (fn x => if x = 0 then NONE else SOME x) [0, 0, 2, 3, 4]; (* 2 *)
first_answer (fn x => if x = 0 then NONE else SOME x) [0, 0]; (* exception NoAnswer *)

map (fn x => if x = 0 then NONE else SOME [x, x+1]) [0, 0];


all_answers (fn x => if x = 0 then NONE else SOME [x, x+1]) [1,2,3];
(* [] *)

(* 2 *)
count_wildcards (ConstructorP
                     ("x",
                      TupleP [Wildcard, ConstP(1), Variable "b",
                              ConstructorP
                                  ("y",
                                   TupleP [Wildcard, Variable "a", Wildcard])]));

(* 10 *)
count_wild_and_variable_lengths (ConstructorP
                     ("x",
                      TupleP [Wildcard, ConstP(1), Variable "hi",
                              ConstructorP
                                  ("y",
                                   TupleP [Wildcard, Variable "hello", Wildcard])]));

check_pat (ConstructorP
               ("x",
                TupleP [Wildcard, ConstP(1), Variable "hi",
                        ConstructorP
                            ("y",
                             TupleP [Wildcard, Variable "hello", Wildcard])]));

match (Const(1), ConstP(1));

first_match (Const(1), [ConstP(1), Wildcard]);

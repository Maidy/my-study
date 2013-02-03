(* Dan Grossman, Coursera PL, HW2 Provided Code *)

(* if you use this function to compare two strings (returns true if the same
   string), then you avoid several of the functions in problem 1 having
   polymorphic types that may be confusing *)
fun same_string(s1 : string, s2 : string) =
    s1 = s2

(* put your solutions for problem 1 here *)

fun all_except_option (s, xs) =
    let
        fun aux(xs, acc) =
            case xs of
                [] => NONE
              | x::xs' => if same_string(x, s)
                          then SOME(acc @ xs')
                          else aux(xs', acc @ [x])
    in
        aux(xs, [])
    end

fun get_substitutions1 (xss, s) =
    case xss of
        [] => []
      | xs::xss' =>
        let
            val xs' = all_except_option(s, xs)
        in
            case xs' of
                NONE => get_substitutions1(xss', s)
             | SOME xs'' => xs'' @ get_substitutions1(xss', s)
        end

fun get_substitutions2 (xss, s) =
    let
        fun aux(xss, acc) =
            case xss of
                [] => acc
              | xs::xss' =>
                let
                    val xs' = all_except_option(s, xs)
                in
                    case xs' of
                        NONE => aux(xss', acc)
                      | SOME xs'' => aux(xss', acc @ xs'')
                end
    in
        aux(xss, [])
    end

fun similar_names (xss, name) =
    let
        val { first = f, middle = m, last = l } = name
        val xs = f :: get_substitutions2(xss, f)
        fun aux(xs) =
            case xs of
                [] => []
              | x::xs' => { first = x, middle = m, last = l } :: aux(xs')
    in
        aux(xs)
    end


(* you may assume that Num is always used with values 2, 3, ..., 10
   though it will not really come up *)
datatype suit = Clubs | Diamonds | Hearts | Spades
datatype rank = Jack | Queen | King | Ace | Num of int 
type card = suit * rank

datatype color = Red | Black
datatype move = Discard of card | Draw 

exception IllegalMove

(* put your solutions for problem 2 here *)

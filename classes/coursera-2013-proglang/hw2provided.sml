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

fun card_color (suit, rank) =
    case suit of
        Spades => Black
      | Clubs => Black
      | _ => Red

fun card_value (suit, rank) =
    case rank of
        Num n => n
      | Ace => 11
      | _ => 10

fun remove_card (cs, c, e) =
    case cs of
        [] => raise e
      | c'::cs' => if c' = c
                   then cs'
                   else c' :: remove_card(cs', c, e)

fun all_same_color (cs) =
    case cs of
        [] => true
      | c::[] => true
      | c1::c2::cs' => (card_color c1 = card_color c2)
                       andalso all_same_color(c2::cs')

fun sum_cards (cs) =
    let
        fun sum (cs, acc) =
            case cs of
                [] => acc
              | c::cs' => sum(cs', acc + card_value(c))
    in
        sum(cs, 0)
    end

fun score (helds, goal) =
    let
        val sum = sum_cards helds
        val pre = if sum > goal then 3 * (sum - goal) else goal - sum
    in
        if all_same_color helds
        then pre div 2
        else pre
    end

fun officiate (cards, moves, goal) =
    let
        fun game (helds, cards, moves) =
            case moves of
                [] => helds
              | Discard card::moves' => game(remove_card(helds, card, IllegalMove), cards, moves')
              | Move::moves' => case cards of
                                    [] => helds
                                  | c::cs => if sum_cards(c::helds) > goal
                                             then c::helds
                                             else game(c::helds, cs, moves')
    in
        score(game([], cards, moves), goal)
    end

fun score_challenge (helds, goal) =
    let
        fun ace_count (cards, count) =
            case cards of
                [] => count
             | (_, Ace)::cards' => ace_count(cards', count + 1)
             | _::cards' => ace_count(cards', count)

        val sum1 = (sum_cards helds)
        val sum2 = sum1 - 10 * ace_count(helds, 0)
        val pre1 = if sum1 > goal then 3 * (sum1 - goal) else goal - sum1
        val pre2 = if sum2 > goal then 3 * (sum2 - goal) else goal - sum2
        val pre = if pre1 < pre2 then pre1 else pre2
    in
        if all_same_color helds
        then pre div 2
        else pre
    end

fun officiate_challenge (cards, moves, goal) =
    let
        fun game (helds, cards, moves) =
            case moves of
                [] => helds
              | Discard card::moves' => game(remove_card(helds, card, IllegalMove), cards, moves')
              | Move::moves' => case cards of
                                    [] => helds
                                  | c::cs => if sum_cards (c::helds) > goal
                                             then c::helds
                                             else game(c::helds, cs, moves')
    in
        score_challenge(game([], cards, moves), goal)
    end

fun careful_player (cards, goal) =
    let
        fun min_discard (card::cards, diff) =
            let
                fun dis (cards, min) =
                    case cards of
                        [] => min
                      | c::cs => if (card_value(c) < diff) andalso
                                   (diff - card_value(c) < diff - card_value(min))
                                then dis(cs, c)
                                else dis(cs, min)
            in
                dis(cards, card)
            end

        fun can_discard (cards, diff) =
            case cards of
                [] => false
              | c::cs => if card_value(c) < diff
                        then true
                        else can_discard (cs, diff)

        fun aux (cards, helds, moves) =
            case cards of
                [] => moves
              | c::cs => if goal > sum_cards(helds) + 10
                         then aux(cs, c::helds, moves @ [Draw])
                         else
                             let
                                 val diff = sum_cards(c::helds) - goal
                             in
                                 if diff = 0
                                 then moves @ [Draw]
                                 else if diff < 0
                                 then aux(cs, c::helds, moves @ [Draw])
                                 else 
                                     if can_discard(helds, diff)
                                     then
                                         let
                                             val min = min_discard(helds, diff)
                                         in
                                             aux(cs, c::remove_card(helds, min, IllegalMove),
                                                 moves @ ((Discard min) :: [Draw]))
                                         end
                                     else moves
                             end
    in
        aux(cards, [], [])
    end

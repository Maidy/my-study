(* Dan Grossman, Coursera PL, HW2 Provided Tests *)

all_except_option("a", ["b", "c"]);
(* NONE *)
all_except_option("a", ["a", "b", "c"]);
(* SOME ["b","c"] *)
all_except_option("a", ["b", "a", "c"]);
(* SOME ["b","c"] *)
all_except_option("a", ["b", "c", "a"]);
(* SOME ["b","c"] *)

get_substitutions1([["Fred","Fredrick"],["Elizabeth","Betty"],["Freddie","Fred","F"]], "Fred");
(* answer: ["Fredrick","Freddie","F"] *)
get_substitutions1([["Fred","Fredrick"],["Jeff","Jeffrey"],["Geoff","Jeff","Jeffrey"]], "Jeff");
(* answer: ["Jeffrey","Geoff","Jeffrey"] *)

similar_names([["Fred","Fredrick"],["Elizabeth","Betty"],["Freddie","Fred","F"]],
              {first="Fred", middle="W", last="Smith"});

(* These are just two tests for problem 2; you will want more.

   Naturally these tests and your tests will use bindings defined 
   in your solution, in particular the officiate function, 
   so they will not type-check if officiate is not defined.
 *)

fun provided_test1 () = (* correct behavior: raise IllegalMove *)
    let val cards = [(Clubs,Jack),(Spades,Num(8))]
	val moves = [Draw,Discard(Hearts,Jack)]
    in
	officiate(cards,moves,42)
    end

fun provided_test2 () = (* correct behavior: return 3 *)
    let val cards = [(Clubs,Ace),(Spades,Ace),(Clubs,Ace),(Spades,Ace)]
	val moves = [Draw,Draw,Draw,Draw,Draw]
    in
 	officiate(cards,moves,42)
    end

card_color(Hearts, 3);
card_value(Clubs, Num(9));
card_value(Clubs, Ace);

val cs = [(Clubs, Num 9), (Clubs, Num 2), (Spades, Ace), (Hearts, King)];
val c1 = (Spades, Ace);
val c2 = (Diamonds, Num 3);
remove_card(cs, c1, IllegalMove);
remove_card(cs, c2, IllegalMove);

all_same_color [(Clubs, Ace), (Clubs, King), (Spades, Queen), (Diamonds, Num 3)];
sum_cards cs; (* 1 + 2 + 11+ 10 = 24 *)
score(cs, 20); (* 4 *)
score([(Clubs, King), (Clubs, Num 3), (Spades, Ace)], 20); (* 2 *)

officiate ([(Clubs, Num 2), (Clubs, Num 3), (Clubs, King), (Clubs, Ace)],
          [Draw, Draw],
          50);

careful_player([(Clubs, Num 2), (Clubs, Num 3), (Clubs, King), (Clubs, Ace), (Clubs 5)], 30);
(*
2 + 3 + 10 + 11
26
Draw, Draw, Draw
*)

careful_player([(Spades,Num 7),(Hearts,King),(Clubs,Ace),(Diamonds,Num 2)], 18);


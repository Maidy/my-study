(* Coursera Programming Languages, Homework 3, Provided Code *)

exception NoAnswer

datatype pattern = Wildcard
		 | Variable of string
		 | UnitP
		 | ConstP of int
		 | TupleP of pattern list
		 | ConstructorP of string * pattern

datatype valu = Const of int
	      | Unit
	      | Tuple of valu list
	      | Constructor of string * valu

fun g f1 f2 p =
    let 
	val r = g f1 f2 
    in
	case p of
	    Wildcard          => f1 ()
	  | Variable x        => f2 x
	  | TupleP ps         => List.foldl (fn (p,i) => (r p) + i) 0 ps
	  | ConstructorP(_,p) => r p
	  | _                 => 0
    end

(**** for the challenge problem only ****)

datatype typ = Anything
	     | UnitT
	     | IntT
	     | TupleT of typ list
	     | Datatype of string

(**** you can put all your code here ****)

(* 1. *)
val only_capitals =
    List.filter (fn x => Char.isUpper(String.sub(x, 0)))

(* 2. *)
val longest_string1 =
    foldl (fn (x, acc) =>
              if String.size(acc) >= String.size(x)
              then acc else x) ""

(* 3. *)
val longest_string2 =
    foldl (fn (x, acc) =>
              if String.size(acc) > String.size(x)
              then acc else x) ""

(* 4. *)
fun longest_string_helper f xs =
    foldl (fn (x, acc) =>
              if f(String.size(x), String.size(acc))
              then x
              else acc) "" xs
val longest_string3 = longest_string_helper (fn (a, b) => a > b)
val longest_string4 = longest_string_helper (fn (a, b) => a >= b)

(* 5. *)
val longest_capitalized = longest_string1 o only_capitals

(* 6 *)
val rev_string = String.implode o rev o String.explode

(* 7*)

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
(* ...
fun typecheck_patterns (ts, ps) =
    case ps of
        [] => NONE
      | p::ps' => case p of
                      Wildcard => NONE
                    | Variable s => NONE
                    | UnitP => NONE
                    | ConstP i => NONE
                    | TupleP ps'' => NONE
                    | ConstructorP (s, ps'') => NONE
    case ts of
        [] => NONE
     | (foo, bar, typ)::ts' => case typ of
                                   Anything => NONE
                                 | UnitT => NONE
                                 | IntT => NONE
                                 | TupleT typs => NONE
                                 | Datatype s => NONE
*)

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
fun first_answer f xs =
    case xs of
        [] => raise NoAnswer
      | x::xs' => case f x of
                      SOME x' => x'
                    | NONE => first_answer f xs'
                                           
(* 8 *)
fun all_answers f xs =
    let
        fun loop (xs, acc) =
            case xs of
                [] => SOME acc
             | x::xs' => case x of
                            NONE => NONE
                          | SOME xss => loop(xs', acc @ xss)
    in
        loop (map f xs, [])
    end

(* 9 *)
val count_wildcards = g (fn w => 1) (fn v => 0)
val count_wild_and_variable_lengths = g (fn w => 1) (fn v => String.size v)
fun count_some_var (str, p) = g (fn w => 0) (fn v => if str = v then 1 else 0) p

(* 10 *)
fun check_pat p =
    let
        fun all_vars p =
            case p of
                Variable x => [x]
              | TupleP ps => foldl (fn (x, acc) => all_vars(x) @ acc) [] ps
              | ConstructorP (_, p) => all_vars p
              | _ => []
                         
        fun distinct xs =
            case xs of
                [] => true
              | x::xs' => not (List.exists (fn (a) => a = x) xs')
                          andalso distinct xs'
    in
        distinct (all_vars p)
    end

(* 11 *)
fun match (valu, pattern) =
    case pattern of
        Wildcard => SOME []
      | Variable s => SOME [(s, valu)]
      | UnitP => (case valu of
                      Unit => SOME []
                    | _ => NONE)
      | ConstP n => (case valu of
                         Const m => if n = m then SOME [] else NONE
                       | _ => NONE)
      | TupleP ps => (case valu of
                          Tuple vs => if List.length vs = List.length ps
                                      then all_answers match (ListPair.zip(vs, ps))
                                      else NONE
                        | _ => NONE)
      | ConstructorP (s1, p) => (case valu of
                                     Constructor (s2, v) => if s1 = s2
                                                            then match (v, p)
                                                            else NONE
                                   | _ => NONE)

(* 12 *)
fun first_match valu patterns =
    SOME (first_answer (fn (pattern) => match(valu, pattern)) patterns)
    handle NoAnswer => NONE

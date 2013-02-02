(*
date : int*int*int : year month day
day of year : 1월 1일부터의 날 수
*)

(* 1. *)
fun is_older (date1 : (int*int*int), date2 : (int*int*int)) =
    if #1 date1 < #1 date2
    then true
    else if #1 date1 > #1 date2
    then false
    else
        if #2 date1 < #2 date2
        then true
        else if #2 date1 > #2 date2
        then false
        else #3 date1 < #3 date2

(* 2. *)
fun number_in_month (dates : (int*int*int) list, month : int) =
    if null dates
    then 0
    else
        if #2 (hd dates) = month
        then 1 + number_in_month (tl dates, month)
        else number_in_month (tl dates, month)

(* 3. *)
fun number_in_months (dates : (int*int*int) list, months : int list) =
    if null months
    then 0
    else number_in_month (dates, hd months) +
         number_in_months (dates, tl months)

(* 4. *)
fun dates_in_month (dates : (int*int*int) list, month : int) =
    if null dates
    then []
    else
        if #2 (hd dates) = month
        then (hd dates) :: dates_in_month (tl dates, month)
        else dates_in_month (tl dates, month)

(* 5. *)
fun dates_in_months (dates : (int*int*int) list, months : int list) =
    if null months
    then []
    else dates_in_month(dates, hd months) @
         dates_in_months(dates, tl months)

(* 6. *)
fun get_nth (words : string list, n : int) =
    if n = 1
    then hd words
    else get_nth (tl words, n - 1)

(* 7. *)
fun date_to_string (date : (int*int*int)) =
    let
        val months = ["January", "February", "March", "April", "May", "June",
                      "July", "August", "September", "October", "November", "December"]
    in
        get_nth(months, #2 date) ^ " " ^ Int.toString(#3 date) ^ ", " ^ Int.toString(#1 date)
    end

(* 8. *)
fun number_before_reaching_sum (sum : int, numbers : int list) =
    let
        fun iter (nums : int list, index : int, subsum : int) =
            if sum <= subsum + (hd nums)
            then index
            else iter (tl nums, index + 1, subsum + (hd nums))
    in
        iter (numbers, 0, 0)
    end

(* 9. *)
fun what_month (day : int) =
    let
        val day_of_month = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    in
        number_before_reaching_sum(day, day_of_month) + 1
    end

(* 10. *)
fun month_range (day1 : int, day2 : int) =
    if day1 > day2
    then []
    else
        let
            fun countup (num1 : int, num2 : int) =
                if num1 = num2
                then [ num2 ]
                else num1 :: countup (num1 + 1, num2)

            fun map_day (days : int list) =
                if null days
                then []
                else what_month (hd days) :: map_day (tl days)
        in
            map_day (countup (day1, day2))
        end

(* 11. *)
fun oldest (dates : (int*int*int) list) =
    if null dates
    then NONE
    else
        let
            fun oldest_nonempty (dates : (int*int*int) list) =
                if null (tl dates)
                then hd dates
                else
                    let
                        val tl_ans = oldest_nonempty (tl dates)
                    in
                        if is_older (hd dates, tl_ans)
                        then hd dates
                        else tl_ans
                    end
        in
            SOME (oldest_nonempty dates)
        end

(* 12. *)
fun contains (x : int, xs : int list) =
    if null xs
    then false
    else if x = (hd xs)
    then true
    else contains (x, tl xs)

fun remove_duplicates (xs : int list) =
    if null xs
    then []
    else
        if contains (hd xs, tl xs)
        then remove_duplicates (tl xs)
        else hd xs :: remove_duplicates (tl xs)

fun number_in_months_challenge (dates : (int*int*int) list, months : int list) =
    let
        fun inner (dates : (int*int*int) list, months : int list) =
            if null months
            then 0
            else
                number_in_month (dates, hd months) + inner (dates, tl months)
    in
        inner (dates, remove_duplicates months)
    end

fun dates_in_months_challenge (dates : (int*int*int) list, months : int list) =
    let
        fun inner  (dates : (int*int*int) list, months : int list) =
            if null months
            then []
            else dates_in_month (dates, hd months) @ inner (dates, tl months)
    in
        inner (dates, remove_duplicates months)
    end

(* 13. *)
fun reasonable_date (date : (int*int*int)) =
    let
        val day_of_month = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        val year = #1 date
        val month = #2 date
        val day = #3 date
        fun get_nth (days : int list, n : int) =
            if n = 1
            then hd days
            else get_nth (tl days, n - 1)
        fun is_leap (y : int) =
            (y mod 400 = 0) orelse
            ((y mod 4 = 0) andalso (not (y mod 100 = 0)))
    in
        if year < 1 orelse
           (month < 1 orelse month > 12) orelse
           day < 1
        then false
        else
            if month = 2 andalso is_leap(year)
            then day <= 29
            else day <= get_nth (day_of_month, month)
    end

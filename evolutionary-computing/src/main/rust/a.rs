use std::fs::File;
use std::io::prelude::*;
use std::io::BufReader;
use std::io::BufWriter;

fn parse_int(s: String) -> i32 {
    return s.parse::<i32>().unwrap();
}

fn main() {
    let solution = File::open("mutation.in").unwrap();
    let mut reader = BufReader::new(solution);

    let mut input_text = String::new();

    reader.read_line(&mut input_text).unwrap();

    let vec_num = input_text.trim().split(" ").collect::<Vec<&str>>();
    let len = parse_int(vec_num[0].to_string());
    let n = parse_int(vec_num[1].to_string());

    let fail = 1.0 / len as f64;
    let succ = 1.0 - fail;

    let outfile = File::create("mutation.out").unwrap();
    let mut writer = BufWriter::new(&outfile);

    for _i in 0..n {
        let mut s1 = String::new();
        reader.read_line(&mut s1).unwrap();

        let mut s2 = String::new();
        reader.read_line(&mut s2).unwrap();

        let mut chars1 = s1.chars();
        let mut chars2 = s2.chars();

        let mut res = 1.0;
        for _j in 0..len {
            if chars1.next().unwrap() != chars2.next().unwrap() {
                res *= fail;
            } else {
                res *= succ;
            }
        }

        writer.write_fmt(format_args!("{}\n", res));
    }
}

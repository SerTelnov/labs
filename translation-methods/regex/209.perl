while (<>) {
    s/\((.*?)\)/()/g;
    print;
}
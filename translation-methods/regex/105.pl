while (<>) {
    print if /[xyz].{5,17}[xyz](.{5,17}[xyz])?/;
}
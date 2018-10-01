while (<>) {
    print if /^\S+(?:\s+\S+)*$|^$/;
}
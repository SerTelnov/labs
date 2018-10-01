# open my , '<', 'input.txt' or die "Cannot open file: $!\n";

my $prev;
  
while (<>) {
    my $line = $_;
  
    $line =~ s/^(\s*)((\s*\S+)*)(\s*)/\2/g;
    $line =~ s/(\s\s+)/ /g;
  
    if ($line =~ /\S+/g) {
        print $line;  
        $prev = $line;
        last;
    }
}
  
my $counter = 0;
  
while (<>) {
    my $line = $_;
  
    $line =~ s/^(\s*)((\s*\S+)*)(\s*)/\2/g;
    $line =~ s/(\s\s+)/ /g;
  
    if ($line =~ /\S+/g) {
        if ($counter >= 1) {
            print "\n";
            $counter = 0;
        }
  
        if ($prev !~ /\n$/) {
            print "\n";
        }
        print $line;

        $prev = $line;
    } else {
        $counter++;
    }
}

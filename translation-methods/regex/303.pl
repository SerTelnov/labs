# open my $fh, '<', 'test0.html' or die "Cannot open file: $!\n";

my @links;
my $counter = 0;

while (<>) {
    foreach my $match (/.*<a(.*?)href\s*=\s*"(.+?)".*/) {
        $match =~ s/.*<a(.*?)href\s*=\s*"(.*?)".*/\2/;
        if ($match =~ /.*?:\/\/(.*:.*\@)?([\w+\.-]+)(([:|\/|?|#|&|=].*)|$)/ or 
                $match =~ /(.*:.*\@)?([\w+\.-]+):\d+([:|\/|?|#|&|=].*)?/) {
            $match =~ s/.*?:\/\/(.*:.*\@)?([\w+\.-]+)(([:|\/|?|#|&|=].*)|$)/\2/;
            $match =~ s/(.*:.*\@)?([\w+\.-]+):\d+([:|\/|?|#|&|=].*)?/\2/;
            @links[$counter++] = $match;
        }
    }
}
 
my @links = sort @links;
my $prev = "";
 
for (my $i = 0; $i != $counter; $i++) {
    my $curr = @links[$i];
    if ($curr ne $prev and $curr ne "") {
        print "$curr\n";
    }
    $prev = $curr;
}

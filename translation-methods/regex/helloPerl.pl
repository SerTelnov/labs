use strict;
use warnings;
 
print "Your name please: ";
my $name = <STDIN>;
chomp $name;
 
print "Your name is '$name'\n";

use strict;
use warnings;

my $str = '  This is temp ok? ok!';
my $regex = qr/^(\s+)((\s*\S+)*)(\s*)\n$/p;
my $subst = '\\2';

my $result = $str =~ s/$regex/$subst/rg;

print "The result of the substitution is' $result\n";

while(<>) {
    my $line = $_;
    print if ($line =~ /\bAPPLE\b/);
}
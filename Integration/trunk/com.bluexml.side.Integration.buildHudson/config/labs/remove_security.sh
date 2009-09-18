
# delete svn folder
for f in `find $1 -type d -name ".svn"`; do
	rm -rf $f
done

for f in `find $1 -type f -name "*.java"`; do
	
	# find the text and replace it
	perl -p -i -e 's/com.bluexml.side.util.security.preferences.SWTResourceManager/com.swtdesigner.SWTResourceManager/g' $f
	perl -p -i -e 's/implements Checkable/ /g' $f
	perl -p -i -e 's/, Checkable \{/ \{/g' $f

	# delete line having the pattern 'com.bluexml.side.Util.security'
	perl -ni -e 'print unless /com.bluexml.side.Util.security/' $f
	perl -ni -e 'print unless /com.bluexml.side.util.security./' $f
	
	
	if grep -n "public boolean check" $f
	then		
		# delete every line betwen the pattern 'public boolean check' and '}'
		perl -pi -e 'if(/public boolean check/../\}/){s/^.*$//s unless /(public boolean check|\})/}' $f		
		line1=`grep -n "public boolean check" $f`
		num=`expr match "$line1" '.*\(^[0-9]*\)'`
		num1=$(($num+1))
		perl -pi -e 'print "return true;\n" if $. == "'$num1'"' $f
		#sed -e ''$num','$num1'd' $f > e.txt
		#mv e.txt $f
	fi
	if grep -n "public static Boolean checkElementValidity" $f
	then		
		# delete every line betwen the pattern 'public boolean check' and '}'
		perl -pi -e 'if(/public static Boolean checkElementValidity/../return null;/){s/^.*$//s unless /(public static Boolean checkElementValidity|return null;)/}' $f		
		line1=`grep -n "public static Boolean checkElementValidity" $f`
		num=`expr match "$line1" '.*\(^[0-9]*\)'`
		num1=$(($num+1))
		perl -pi -e 's/return null/return true/ if $. == "'$num1'"' $f
		#sed -e ''$num','$num1'd' $f > e.txt
		#mv e.txt $f
	fi
done

for f in `find $1 -type f -name "feature.xml"`; do

	perl -0 -p -e 's/( *)<plugin( *)(\s+)( *)id="com.bluexml.side.Util.security"[^<]*//sg' $f
done

for f in `find $1 -type f -name "*.xml"`; do
	
	
	# delete line having the pattern 'com.bluexml.side.Util.security'
	perl -ni -e 'print unless /com.bluexml.side.Util.security/' $f
done

for f in `find $1 -type f -name "*.MF"`; do
	
	
	# delete line having the pattern 'com.bluexml.side.Util.security'
	perl -ni -e 'print unless /com.bluexml.side.Util.security/' $f
done

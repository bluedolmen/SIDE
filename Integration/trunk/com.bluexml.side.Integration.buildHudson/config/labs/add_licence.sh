licence=$1
chemin=$2

oldIFS=$IFS     
IFS=$'\n'    

var=`cat $licence`


# avoid webscript js files
for f in `find $chemin -type f -name "*.java" `; do
	filein=$f
    echo "add license header in : $f"
	num=1
	perl -pi -le 'print "/*" if $. == "'$num'"' $filein
	num=$(($num+1))
	for i in $var; do
        	perl -pi -le 'print "'$i'" if $. == "'$num'"' $filein
		num=$(($num+1))
	done

	perl -pi -le 'print "*/" if $. == "'$num'"' $filein
done

#for f in `find $chemin -type f -name "*.xml" `; do
#	filein=$f
#	num=2
#	perl -pi -le 'print "<!--" if $. == "'$num'"' $filein
#	num=$(($num+1))
#	for i in $var; do
#        	perl -pi -le 'print "'$i'" if $. == "'$num'"' $filein
#		num=$(($num+1))
#	done
#
#	perl -pi -le 'print "-->" if $. == "'$num'"' $filein
#done

#for f in `find $chemin -type f -name "*.ecore" -o -name "*.ecore_diagram" -o -name "*.ecorediag" -o -name "*.genmodel" -o -name "*.chain" -o -name "*.exsd" `; do
#	filein=$f
#	num=1
#	perl -pi -le 'print "<!--" if $. == "'$num'"' $filein
#	num=$(($num+1))
#	for i in $var; do
#       	perl -pi -le 'print "'$i'" if $. == "'$num'"' $filein
#		num=$(($num+1))
#	done
#
#	perl -pi -le 'print "-->" if $. == "'$num'"' $filein
#done

#for f in `find $chemin -type f -name "*.properties"`; do
#	filein=$f
#	num=1
#	for i in $var; do
#        	perl -pi -le 'print "#  '$i'" if $. == "'$num'"' $filein
#		num=$(($num+1))
#	done
#
#done

for f in `find $chemin -type f -name "*.mt" `; do
	filein=$f
    echo "add license header in : $f"
	num=1
	perl -pi -le 'print "<%--" if $. == "'$num'"' $filein
	num=$(($num+1))
	for i in $var; do
        	perl -pi -le 'print "'$i'" if $. == "'$num'"' $filein
		num=$(($num+1))
	done

	perl -pi -le 'print "--%>" if $. == "'$num'"' $filein
done

# add license.txt into all project

for f in `find $chemin -type f -name ".project" `; do
path=${f%/*}
echo "add license file in $path"
cp $licence $path/license.txt
done


IFS=$old_IFS

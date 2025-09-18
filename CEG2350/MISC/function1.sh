echo Enter base wage:
read base
echo Enter weekly hours:
read hours 


calcSalary $base $hours
function calcSalary ( ) {
salary=$1*$2
echo $salary
}

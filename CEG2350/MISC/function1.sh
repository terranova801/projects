echo Enter base wage:
read base
echo Enter weekly hours:
read hours 


calcSalary $base $hours
function calcSalary ( ) {
salary=$base*$hours
echo $salary
}

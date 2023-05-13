param (
  [Parameter(Mandatory=$true)]
  [string]$FilePath1
)

[int]$errorCounter = 0
$global:failedTestsCounter
$global:failedTestsName = @()
$global:numberofTests
function CompareTwoFiles
{
	param (
		[string]$path
	)
	[string]$testcase_Out_Path = "Outputs\" + $path
	$testcase_Out = Get-Content $testcase_Out_Path
	Write-Host "\Inputs\$path"
	"cd src && javac Main.java && java Main.java Inputs\$path" | CMD
	$testcase_ActualOut_Path = "ActualOutputs\" + $path
	$testcase_ActualOut = Get-Content $testcase_ActualOut_Path
	
	if ($testcase_Out.Length -ne $testcase_ActualOut.Length)
	{
		Write-Host "`nTESTING THE TEST CASE NAMED: $($path.substring(0, $path.Length - 4))"
		Write-Host "Mar a hossz sem egyezik kekw!" -ForeGroundColor Red
		$global:failedTestsCounter++
		$global:failedTestsName += $($path.substring(0, $path.Length - 4))
	}else{
	Write-Host "`nTESTING THE TEST CASE NAMED: $($path.substring(0, $path.Length - 4))"
	for ($i = 0; $i -lt $testcase_Out.Length; $i++)
	{
		if ($testcase_Out[$i] -ne $testcase_ActualOut[$i])
		{
			Write-Host "*** LINE FAILED BELOW ***" -ForegroundColor Red
			Write-Host "EXPECTED: $($testcase_Out[$i])" -ForegroundColor Red
			Write-Host "GOT: $($testcase_ActualOut[$i])" -ForegroundColor Red
			$errorCounter++
		}
		else
		{
			Write-Host "EXPECTED: $($testcase_Out[$i])" -ForegroundColor Green
			Write-Host "GOT: $($testcase_ActualOut[$i])" -ForegroundColor Green
		}
	}
		if ($errorCounter -gt 0)
		{
			$global:failedTestsCounter++
			$global:failedTestsName += $($path.substring(0, $path.Length - 4))
			Write-Host "`nRESULT: " -ForegroundColor Red
			Write-Host "Test: $($path.substring(0, $path.Length - 4)) FAILED!" -ForegroundColor Red
			
		}
		else
		{
			Write-Host "`nRESULT: " -ForegroundColor Green
			Write-Host "Test: $($path.substring(0, $path.Length - 4)) SUCCEEDED!" -ForegroundColor Green
		}
	}
	
}

if ($FilePath1 -ne "all")
{
	CompareTwoFiles "$FilePath1"
}
else
{
	$tests = Get-ChildItem "Inputs\"
	$global:numberofTests = $tests.Count
	foreach ($t in $tests)
	{
		$testinput_Name = $t.Name
		CompareTwoFiles "$testinput_Name"
	}
	if ($global:failedTestsCounter -gt 0)
	{
		Write-Host "`nTHE FOLLOWING TESTS FAILED: " -ForeGroundColor Red
		foreach ($actual in $global:failedTestsName)
		{
			Write-Host "Test: $actual FAILED!" -ForeGroundColor Red
		}
		Write-Host "$global:failedTestsCounter\$global:numberofTests have failed :((((" -ForeGroundColor Red
	}
	else
	{
		Write-Host "`nAll tests have passed! :)" -ForeGroundColor Green
	}
}


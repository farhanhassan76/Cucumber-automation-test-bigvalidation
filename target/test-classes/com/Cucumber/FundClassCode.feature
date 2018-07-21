Feature: open Browser
@myTest
Scenario: Open MicroStrategy

Given I open the login /MicroStrategy/asp/Main.aspx page
When I enter Administrator in the Uid field
When I enter PutnaM@123 in the Pwd field
And I click on the button 3054
When I click on the element using Xpath //*[@id="projects_ProjectsStyle"]/table/tbody/tr[2]/td[1]/div/table/tbody/tr/td[2]/a

#I am in Putnam LGFF SQA folder now
When I wait for dktpSectionView to visible
When I click on the element using Xpath //*[@id="dktpSectionView"]/a[1]

#I am in Shared reports folder now
When I wait for folderAllModes to visible
And I wait some seconds
When I click on the element using Xpath //*[@id="FolderIcons"]/tbody/tr[1]/td[2]/div
And I wait some seconds

#I am in QA Testing folder now
And I wait some seconds
When I click on the element using Xpath  //*[@id="FolderIcons"]/tbody/tr[5]/td[1]/div
And I wait some seconds

#Getting data from Table
#And I get data from requested table
And I CompareData for FundClassCode




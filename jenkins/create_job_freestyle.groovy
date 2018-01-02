import jenkins.model.*;
import hudson.security.*;
import jenkins.install.*;
import hudson.triggers.SCMTrigger;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;



jenkins = Jenkins.instance;
gitTrigger = new SCMTrigger("* * * * *");


jobNameDev = "dev";
dslProjectDev = new hudson.model.FreeStyleProject(jenkins, jobNameDev);
dslProjectDev.addTrigger(gitTrigger);
jenkins.add(dslProjectDev, jobNameDev);

jobNameStg = "stg";
dslProjectStg = new hudson.model.FreeStyleProject(jenkins, jobNameStg);
dslProjectStg.addTrigger(gitTrigger);
jenkins.add(dslProjectStg, jobNameStg);

jobNamePrd = "prd";
dslProjectPrd = new hudson.model.FreeStyleProject(jenkins, jobNamePrd);
dslProjectPrd.addTrigger(gitTrigger);
jenkins.add(dslProjectPrd, jobNamePrd);


jobDev = jenkins.getItem(jobNameDev);
builders = jobDev.getBuildersList();

hudson.tasks.Shell newShell = new hudson.tasks.Shell("echo \"Hello\" ")
builders.replace(newShell)

gitTrigger.start(dslProjectDev, true);






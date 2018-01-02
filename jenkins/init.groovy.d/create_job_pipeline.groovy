#!groovy
import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*
import hudson.security.*
import hudson.tools.*
import jenkins.model.Jenkins
import org.jenkinsci.plugins.workflow.job.WorkflowJob
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition
import hudson.plugins.git.GitSCM
import java.util.logging.Logger
import hudson.plugins.git.*;
import hudson.triggers.SCMTrigger

def pipeline_jobs = ["master", "staging", "prd"]

def pipeline_repo = "https://github.com/kfengbest/microservice-template.git"
def pipeline_file = "Jenkinsfile"

def userRemoteConfig = new UserRemoteConfig(pipeline_repo, null, null, null)
def scmTrigger = new SCMTrigger('* * * * *')


pipeline_jobs.each{
  def branchName = "*/" + it;
  def scm = new GitSCM(
    Collections.singletonList(userRemoteConfig),
    Collections.singletonList(new BranchSpec( branchName )),
    false,
    Collections.<SubmoduleConfig>emptyList(),
    null,
    null,
    null)
  WorkflowJob job = Jenkins.instance.createProject(WorkflowJob, it);
  def definition = new CpsScmFlowDefinition(scm,pipeline_file)
  definition.lightweight = true
  job.definition=definition
  job.addTrigger(scmTrigger)
  job.save()
}


Jenkins.instance.reload()




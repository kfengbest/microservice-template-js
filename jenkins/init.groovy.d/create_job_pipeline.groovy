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
import groovy.json.JsonSlurper

import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*

def textJson = new File("/var/jenkins_home/init.groovy.d/git.json").text
def jsonSlurper = new JsonSlurper()
def gitJson = jsonSlurper.parseText(textJson)

def pipeline_jobs = gitJson.branch
def pipeline_repo = gitJson.repo
def pipeline_file = "Jenkinsfile"

def idMatcher = CredentialsMatchers.withId("global_usnp_git");
available_credentials = CredentialsProvider.lookupCredentials(UsernamePasswordCredentials.class)
existing_credentials =CredentialsMatchers.firstOrNull( available_credentials, idMatcher )

def userRemoteConfig = new UserRemoteConfig(pipeline_repo, null, null, existing_credentials.id)
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




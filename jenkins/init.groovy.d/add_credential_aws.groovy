
import com.cloudbees.plugins.credentials.impl.*;
import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.domains.*;
import com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey
import com.cloudbees.jenkins.plugins.awscredentials.AWSCredentialsImpl
import org.jenkinsci.plugins.plaincredentials.StringCredentials

Credentials c = (Credentials) new AWSCredentialsImpl(CredentialsScope.GLOBAL, "globa_cred_id_aws","aws_accesskey", "aws_access_secrete", "desc")
SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), c)


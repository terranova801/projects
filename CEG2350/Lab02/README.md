## Lab 02

- Name: Alex Feyh
- Email: feyh.2@wright.edu

## Part 1 Answers

Full / absolute path to your private key file: 
/media/alex/Shared_4/WSU/"Fall 25"/"CEG 2350 - Operating Sytem Concepts and Usage"/Lab/Lab02/ceg2350-aws-vm.pem

Command to SSH to AWS instance:
```
ssh -i ceg2350-aws-vm.pem ubuntu@3.231.254.200

```

## Part 2 Answers

1. `chmod u+r bubbles.txt`
    - Means: This grants read permissions of bubbles.txt for the user owner, without removing any other permissions previously granted
    - Assessment: This is typically a neccessity for any text files. If any permission is to be granted, its usually a read permission. Imagine trying to write to a file without having access to read that same file.
2. `chmod u=rw,g-w,o-x banana.cabana`
    - Means: This overwrites previous permissions for user, granting read/write, removes group write permissions & removes other execute permissions
    - Assessment: This is an appropriate permission structure, although if this file is meant to be executable, nobody has permissions to execute. Also the group has identical permissions to other users, so this file really doesn't need to be associated with this group at all.
3. `chmod a=w snow.md`
    - Means: Overwrites previous permissions for everyone to write only
    - Assessment: Not recommended as this is a markdown file and writing to a file without having read access is essentially useless. Also owner of file should usually have both r/w. Others can write to file which is not recommended.
4. `chmod 751 program`
    - Means: Changes permissions of program: rwx for user, r-x for group, --x for other
    - Assessment: Okay permission structure if you would like other users to be able to execute a program but not have access to reading/writing.
5. `chmod -R ug+w share`
    - Means: Recursively grants new write permissions to the user and group without removing any permissions
    - Assessment: Effective for granting write access to a directory, and all of its subdirectory using the recursive option of chmod

## Part 3 Answers

1. Command to create new user: sudo adduser #username#
2. Path to new user's home directory: /home/afeyh
3. Evaluate if `ubuntu` can add files to new user's home directory: False
4. Command to switch to new user: su afeyh
5. Command(s) to go to new user's home directory: cd /home/afeyh
6. Evaluate if new user can add files to user's home directory: True
7. Command to return to `ubuntu` user: exit 
8. Command to return to `ubuntu` home directory: cd /home/ubuntu 

## Part 4 Answers

1. Command(s) to create group named `squad` and add members: sudo addgroup squad
2. Command(s) to add `ubuntu` & user to group `squad`: sudo usermod -aG squad ubuntu | sudo usermod -aG squad afeyh
3. Command(s) to allow `squad` to view the `ubuntu` user's home directory contents: sudo chgrp squad /home/ubuntu | sudo chmod 750 ubuntu
4. Command(s) to modify `share` to have group ownership of `squad`: sudo chgrp squad share | sudo chmod 770 share
5. Describe your tests and commands with the user account: 
Using afeyh user account
cd /home/ubuntu | testing group access to ubuntu home directory -> permission granted
touch testfile.txt | testing write access to ubuntu home directory -> permission denied

cd /home/share | testing access to shared directory -> permission granted
touch testfile.txt | testing share group permissions -> permission granted
rm testfile.txt | removing testfile

/home$ ls -l ->  
drwxr-x--- 4 afeyh  afeyh 4096 Sep  7 05:02 afeyh
drwxrwx--- 2 root   squad 4096 Sep  7 17:07 share
drwxr-x--- 4 ubuntu squad 4096 Sep  7 05:08 ubuntu

6. Describe the full set of permissions / settings that enable the user to make edits:
For the share directory, with 770 permissions, the users of the group squad are able to read, write and execute within the directory. This requires squad be the owner of the share directory. The user afeyh is a member of the group 'squad' and therefore can read, write and execute with the share directory.


## Part 5 Answers

For each, write the command used or answer the question posed.

1. Command(s) to make file using `sudo`: sudo > madewithsudo.txt | Path to file /home/ubuntu
2. Command(s) to make file with `root`: sudo -i | /home/ubuntu > madewithroot.txt | Path to file /home/ubuntu
3. Describe / compare ownership and permissions of files: Ownership using 'root' is root(owner)/root(group) & permissions are 644. Ownership & group using 'sudo' remains with ubuntu and the permissions are instead 664. 
4. Which account can do what actions? (Type Y or N in columns)

Contents inside of `share`
| Account   | Can View  | Can Edit  | Can Change Permissions    |
| ---       | ---       | ---       | ---                       |
| `root`    |   Y        |   Y      |   Y                        |
| `ubuntu`  |     Y      |      Y     |      Y                     |
| `BOB`     |     Y      |      Y    |      N                     |

`madewithsudo.txt`
| Account   | Can View  | Can Edit  | Can Change Permissions    |
| ---       | ---       | ---       | ---                       |
| `root`    |     Y      |   Y        |      Y                     |
| `ubuntu`  |        Y   | Y          |                        Y   |
| `BOB`     |      Y        |      Y     |           N                |

5. Command(s) to modify permissions: sudo chgrp squad madewithsudo.txt | chmod 660 madewithsudo.txt

6. How to give user account `sudo`: sudo -i | usermod -aG sudo afeyh //Need to switch to root and add the user to the sudo group


## Citations
[RedHat - Linux File Permissions Explained](https://www.redhat.com/sysadmin/linux-file-permissions-explained)
[Linuxize - Understanding File Permissions](https://linuxize.com/post/understanding-linux-file-permissions/)
[Debian Wiki](https://wiki.debian.org/)

To add citations, provide the site and a summary of what it assisted you with.  If generative AI was used, include which generative AI system was used and what prompt(s) you fed it.
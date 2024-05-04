
#cd Tickets
#git config --global user.email "jacek.milewski.k@gmail.com"
#git config --global user.name "Jacek Milewski"



git reset --hard HEAD
git fetch origin
git checkout -b test_branch origin/test_branch
git rebase origin/main
git push origin test_branch --force

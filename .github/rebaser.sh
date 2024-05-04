
#cd Tickets
#git config --global user.email "jacek.milewski.k@gmail.com"
#git config --global user.name "Jacek Milewski"




#git reset --hard HEAD
#git fetch origin
#git checkout -b test_branch origin/test_branch
#git rebase origin/main
#git push origin test_branch --force
#git checkout -b main origin/main

for branch in `git branch -r | grep -v HEAD | grep 'test_b' | cut -d/ -f2-`;do
  branch_name=$(echo -e `git show --format="%ci" $branch | head -n 1` \\t$branch)

  echo $branch_name

  git reset --hard HEAD
  git fetch origin
  git checkout -b $branch_name origin/$branch_name
  git rebase origin/main
  git push origin $branch_name --force
  git checkout -b main origin/main

done | sort -r

read
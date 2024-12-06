
for branch in `git branch -r | grep -v HEAD | grep 'origin/x-' | cut -d/ -f2-`;do

  echo "===========rebase $branch?"
  read

  echo "-------aborting outstanding old rebase"
  git rebase --abort
  git checkout main
  git reset --hard

  if git show-ref --verify --quiet refs/heads/$branch; then
    echo "-------remove local branch $branch"
    git branch -D $branch
    echo "-------checkout local branch $branch"
    git checkout $branch
    echo "-------update"
    git fetch origin
    echo "-------merge"
    git merge origin/$branch
  else
    echo "-------Create new local branch for $branch?"
#    read
    git checkout -b $branch origin/$branch
  fi

  echo "-------rebase $branch on main?"
#  read
  git rebase origin/main

#  prefixed_branch=x-$branch

#  echo "-------add prefix?"
#  read
#  git checkout -b $prefixed_branch

  echo "-------force push branch $branch?"
  read
  git push -u origin $branch --force

done

echo "===========done"
#read


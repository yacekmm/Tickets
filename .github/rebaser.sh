
for branch in `git branch -r | grep -v HEAD | grep 'origin/x-' | cut -d/ -f2-`;do

  echo "===========rebase $branch?"
  read

  echo "-------abort outstanding old rebase?"
  read
  git rebase --abort

  if git show-ref --verify --quiet refs/heads/$branch; then
    echo "-------checkout local branch $branch?"
    read
    git checkout $branch
    echo "-------update?"
    read
    git fetch origin

    echo "-------merge?"
    read
    git merge origin/$branch
  else
    echo "-------Create new local branch for $branch?"
    read
    git checkout -b $branch origin/$branch
  fi

  echo "-------rebase $branch on main?"
  read
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
read


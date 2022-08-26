NETWORK_CONFIG="awsvpcConfiguration={subnets=[subnet-04507777954efa5c9],securityGroups=[sg-0d73d3ae8f02b6e3f],assignPublicIp=ENABLED}"

aws ecs list-tasks \
--cluster fish \
| jq -r '.taskArns[]'

taskCount=$(aws ecs list-tasks \
--cluster fish \
| jq -r '.taskArns[]' | xargs -n 1 -I{} aws ecs list-tags-for-resource --resource-arn {} \
| jq -r '.tags[0].value' \
| grep BS0103 \
| wc -l)

echo '****'
echo ${taskCount}
echo '****'

taskArn=$(aws ecs run-task \
--cluster fish \
--task-definition batch \
--network-configuration "${NETWORK_CONFIG}" \
--launch-type FARGATE \
--tags key=jobname,value=BS0103 \
| jq -r '.tasks[0].taskArn')

echo '****'
echo ${taskArn}
echo '****'

aws ecs list-tags-for-resource \
--resource-arn ${taskArn}




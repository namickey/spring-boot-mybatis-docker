# AWS

## VPC

【AWS①】ネットワークを構築してみる  
https://zenn.dev/oreo2990/articles/bf3112bb6ccb48  

### IPアドレス

- VPC             :10.0.0.0/16 65,536個のIP
- PUBLIC  SUBNET 1:10.0.1.0/24    256個のIP
- PUBLIC  SUBNET 2:10.0.2.0/24    256個のIP
- PRIVATE SUBNET 3:10.0.3.0/24    256個のIP
- PRIVATE SUBNET 4:10.0.4.0/24    256個のIP

### AZ

- PUBLIC  SUBNET 1: ap-northeast-1a
- PUBLIC  SUBNET 2: ap-northeast-1b
- PRIVATE SUBNET 3: ap-northeast-1a
- PRIVATE SUBNET 4: ap-northeast-1b

### igw

- `VPC`にigwがアタッチされていること
- `VPC`のルートテーブルにigwが設定されていること
- `PUBLIC SUBNET`のルートテーブルにigwが設定されていること
- `PRIVATE SUBNET`のルートテーブルには、igwが設定されていないこと  
  ※igw=インターネットゲートウェイ

### セキュリティグループ

- サーバ種類の単位に作成
- 例えば、作業用にインバウンドでSSHを許可する

### 構成

- 例えば、PUBLIC  SUBNETには、API GW、ALB、WEBサーバ、踏み台
- 例えば、PRIVATE SUBNETには、DB、EC2内部サーバ（APサーバ、バッチサーバ）、ECS（サービス、タスク）

## EC2

【AWS】EC2インスタンスの作成方法解説！サーバーを作成して接続してみる  
https://engineer-ninaritai.com/aws-ec2-make/  

- VPCを選択
- SUBNETを選択
- セキュリティグループを選択
- OS
- インスタンスタイプ ※1年間無料有り
- ディスク
- キーペア

接続方法  
```
chmod 400 key.pem
ssh -i key.pem ec2-user@IPアドレス
```




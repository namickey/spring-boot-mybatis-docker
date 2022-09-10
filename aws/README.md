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
- 例えば、作業用にpublic subnet向けインバウンドでSSHを許可する





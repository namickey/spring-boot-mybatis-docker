# AWS

## VPC

�yAWS�@�z�l�b�g���[�N���\�z���Ă݂�  
https://zenn.dev/oreo2990/articles/bf3112bb6ccb48  

### IP�A�h���X

- VPC             :10.0.0.0/16 65,536��IP
- PUBLIC  SUBNET 1:10.0.1.0/24    256��IP
- PUBLIC  SUBNET 2:10.0.2.0/24    256��IP
- PRIVATE SUBNET 3:10.0.3.0/24    256��IP
- PRIVATE SUBNET 4:10.0.4.0/24    256��IP

### AZ

- PUBLIC  SUBNET 1: ap-northeast-1a
- PUBLIC  SUBNET 2: ap-northeast-1b
- PRIVATE SUBNET 3: ap-northeast-1a
- PRIVATE SUBNET 4: ap-northeast-1b

### igw

- `VPC`��igw���A�^�b�`����Ă��邱��
- `VPC`�̃��[�g�e�[�u����igw���ݒ肳��Ă��邱��
- `PUBLIC SUBNET`�̃��[�g�e�[�u����igw���ݒ肳��Ă��邱��
- `PRIVATE SUBNET`�̃��[�g�e�[�u���ɂ́Aigw���ݒ肳��Ă��Ȃ�����  
  ��igw=�C���^�[�l�b�g�Q�[�g�E�F�C

### �Z�L�����e�B�O���[�v

- �T�[�o��ނ̒P�ʂɍ쐬
- �Ⴆ�΁A��Ɨp�ɃC���o�E���h��SSH��������

### �\��

- �Ⴆ�΁APUBLIC  SUBNET�ɂ́AAPI GW�AALB�AWEB�T�[�o�A���ݑ�
- �Ⴆ�΁APRIVATE SUBNET�ɂ́ADB�AEC2�����T�[�o�iAP�T�[�o�A�o�b�`�T�[�o�j�AECS�i�T�[�r�X�A�^�X�N�j

## EC2

�yAWS�zEC2�C���X�^���X�̍쐬���@����I�T�[�o�[���쐬���Đڑ����Ă݂�  
https://engineer-ninaritai.com/aws-ec2-make/  

- VPC��I��
- SUBNET��I��
- �Z�L�����e�B�O���[�v��I��
- OS
- �C���X�^���X�^�C�v ��1�N�Ԗ����L��
- �f�B�X�N
- �L�[�y�A

�ڑ����@  
```
chmod 400 key.pem
ssh -i key.pem ec2-user@IP�A�h���X
```




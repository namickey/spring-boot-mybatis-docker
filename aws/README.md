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
- �Ⴆ�΁A��Ɨp��public subnet�����C���o�E���h��SSH��������





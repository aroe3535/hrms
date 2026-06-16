# HRMS（人材管理システム）

## ■ 概要

社内の人材・部署情報を管理する業務アプリケーションです。
実務で頻出する「マスタ管理（部署管理）」を題材に、設計からデプロイまで一貫して開発しました。

単なるCRUD実装に留まらず、**レイヤー設計・論理削除・本番環境デプロイ**までを意識した構成としています。

---

## ■ デモ環境

https://hrms-production-36d1.up.railway.app/departments

※ 現在、デプロイ先を載せ替え中のためアクセスできません。

---

## ■ 実装機能

### ▼ 基本機能

* 部署一覧表示
* 部署新規登録
* 部署編集
* 部署削除（論理削除）
* 処理結果メッセージ表示

### ▼ 設計を意識した機能

* 論理削除（deleted_flg）
* レイヤー分離（Controller / Service / Repository）
* バリデーション（Bean Validation）

---

## ■ 技術スタック

* Java 17
* Spring Boot
* Spring Data JPA（Hibernate）
* PostgreSQL
* Thymeleaf
* Maven
* Railway（デプロイ）
* GitHub（コード管理）

---

## ■ 技術選定理由

| 技術              | 選定理由                  |
| --------------- | --------------------- |
| Spring Boot     | 実務利用が多く、レイヤー設計を学ぶため   |
| Spring Data JPA | ORMの理解とDBマッピング経験を得るため |
| PostgreSQL      | 実務利用が多いRDBを採用         |
| Thymeleaf       | サーバーサイドレンダリング理解のため    |

---

## ■ アーキテクチャ構成

Controller
↓
Service
↓
Repository
↓
PostgreSQL

※ Controllerにロジックを集中させず、Service層を設けることで責務分離を意識しています

---

## ■ 設計上の工夫

### ① 論理削除設計

物理削除ではなく `deleted_flg` を用いた論理削除を採用。
実務でのデータ保全を想定した設計としています。

---

### ② JPAとDBスキーマの整合性対応

Entity定義とDBスキーマの不整合により、起動時エラーが発生。

【対応内容】

* 主キー定義の見直し
* カラム型の統一
* マッピングアノテーション修正

ログを確認し原因を切り分け、設計を修正しました。
→ ORM設計における整合性担保の重要性を学びました。

---

### ③ デプロイ時のトラブル対応

Railway環境でDB接続エラーが発生。

【原因】
環境変数設定ミス

【対応】
ログ確認 → 接続URL修正 → 再デプロイ

→ 本番環境を想定したトラブルシューティングを経験しました。

---

## ■ ER図（ローカル環境）

| カラム名            | 説明          |
| --------------- | ----------- |
| id              | 主キー         |
| department_code | 部署コード（ユニーク） |
| department_name | 部署名         |
| deleted_flg     | 論理削除フラグ     |
| created_at      | 作成日時        |
| updated_at      | 更新日時        |

---

## ■ 処理フロー（部署一覧画面）

1. ユーザーが部署一覧画面にアクセス
2. Controllerがリクエストを受け取りServiceへ処理依頼
3. ServiceがRepository経由でDBからデータ取得
4. ControllerがModelへ格納
5. Thymeleafで一覧表示

---

## ■ 今後の改善点

* Bean Validationの強化
* ログ出力（Logback）追加
* テストコード実装（JUnit）
* Spring Security導入（認証・認可）

---

## ■ 開発を通じて得た知見

* Spring Bootにおけるレイヤー構造の理解
* JPAとDBスキーマ整合性の重要性
* ログを用いた原因特定・トラブルシュート
* 本番環境デプロイ時の設定管理

---

## ■ URL

https://hrms-production-36d1.up.railway.app/departments
